import parse_bible
from bs4 import BeautifulSoup

def parse_sins():
    connection = parse_bible.connect_to_db()
    with open('sins.html', 'r') as f:
        raw_html = f.read()
        soup = BeautifulSoup(raw_html, 'html.parser')

        for table in soup.findAll('tbody'):
            for row in table.findAll('tr'):
                columns = row.findAll('td')
                if len(columns) != 3 or columns[0].get_text().strip() == '':
                    continue
                sql_insert_query = """INSERT INTO `proofs_temp` (`title`, `bible_links`) VALUES (%s, %s)"""
                sin_title = ' '.join(columns[1].get_text().strip().split()).replace('вЂ™', "'").upper()
                val = (sin_title, columns[2].get_text())
                parse_bible.run_sql(connection, sql_insert_query, val)

if __name__ == '__main__':
    parse_sins()