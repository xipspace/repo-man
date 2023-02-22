#
# @xipspace
# verify url status
#

import requests
import time
import validators

# get current date
current_date = time.strftime("%Y%m%d_%H%M%S")

print("\nchecking links...\n")

# files to handle
with open("links.txt", encoding="utf-8") as r, open(current_date + ".log", "w", encoding="utf-8") as w:
	for line in r:
		
		# verify if line is a valid address
		if validators.url(line.strip()) == True:
			
			try:
				# get request
				result = requests.get(line.strip(), allow_redirects=False, timeout=3)
				
				# log result
				print(result.status_code, " > ", result.url)
				w.write(str(result.status_code) + " > " + result.url + "\n")
				
				# log redirects
				if result.is_permanent_redirect == True:
					
					print("redirected to > ", result.headers["location"])
					w.write("redirected to > " + result.headers["location"] + "\n")

			except requests.exceptions.ConnectionError:
				print("error connecting >", line.strip())
				w.write("error connecting > " + line)
			except requests.exceptions.Timeout:
				print("timeout error >", line.strip())
				w.write("timeout error > " + line)
			except requests.exceptions.RequestException as err:
				print("exception : ", err)

print("\ntask concluded")
