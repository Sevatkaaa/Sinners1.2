import mysql.connector
import sys
from bs4 import BeautifulSoup
import json
import requests

def devtrace(text):
    if 'devtrace' in sys.argv:
        print(text)

def connect_to_db(host_url="127.0.0.1", port="3306", db_name="sinners"):
    connection = None
    try:
        connection = mysql.connector.connect(
            host=host_url,
            user="root",
            database=db_name,
            port=port
        )
        if connection:
            devtrace(f"Successfully connected to {db_name}")
        else:
            raise Exception("Connection is None")
    except:
        devtrace(f"Couldn't connect to {db_name}")
    finally:
        return connection

def parse_books(file_name):
    books = {}

    with open(file_name, 'r') as f:
        raw_html = f.read()
        soup = BeautifulSoup(raw_html, 'html.parser')

        for book_row in soup.find_all('tr'):
            if 'ot-book' in book_row.get('class', '/'):
                book_title = ''.join([i for i in book_row.get_text() if not i.isdigit()]).strip()
                chapters = book_row.findAll('td')[1]
                
                for anchor in chapters.findAll('a'):
                    url = 'https://www.biblegateway.com' + anchor.get('href')
                    chapter_number = anchor.get_text()
                    books[book_title] = books.get(book_title, []) + [(chapter_number, url)]
    return books

def run_sql(connection, query, val=None):
    cursor = connection.cursor()
    result = cursor.execute(query, val)
    connection.commit()
    cursor.close() 

def fill_tables(books, connection=None):
    if connection is None:
        connection = connect_to_db()
    for i, title in enumerate(books.keys()):
        sql_insert_query = """INSERT INTO `books` (`title`) VALUES (%s)"""
        val = (title, )
        run_sql(connection, sql_insert_query, val)
        print(i, title, "- DONE")

def get_verses_texts(books):
    def parse_chapter(url):
        headers = requests.utils.default_headers()
        headers.update({
            'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0',
        })
        r = requests.get(url, headers)
        content = r.content
        response = json.loads(content)
        try:
            verses = response['verses']
            if verses:
                return verses
        except:
            return None

    connection = connect_to_db()

    for i, title in enumerate(books.keys()):
        for chapter_id, url in books[title]:
            url = f'https://bible-api.com/{title}+{chapter_id}'
            result = parse_chapter(url)
            if result is None:
                break
            for verse in result:
                sql_insert_query = """INSERT INTO `verses` (`book_id`, `chapter_number`, `verse_number`, `text`) VALUES (%s, %s, %s, %s)"""
                val = (i+1, verse['chapter'], verse['verse'], verse['text'])
                run_sql(connection, sql_insert_query, val)
            print(f'{title}.{chapter_id} - Done')


if __name__ == "__main__":
    books = parse_books('bible.html')
    fill_tables(books)
    get_verses_texts(books)