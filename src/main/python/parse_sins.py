def parse_sins():
  with open(file_name, 'r') as f:
    raw_html = f.read()
    soup = BeautifulSoup(raw_html, 'html.parser')

  for table in soup.findAll('tbody'):
    for row in table.findAll('tr'):
      columns = row.findAll('td')
	  