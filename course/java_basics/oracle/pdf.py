from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import time

# Set up Chrome options to save as PDF
chrome_options = Options()
chrome_options.add_experimental_option('prefs', {
    "printing.print_preview_sticky_settings.appState": '{"recentDestinations":[{"id":"Save as PDF","origin":"local","account":"","capabilities":{}}]}',
    "savefile.default_directory": "/path/to/save/pdfs",  # Change to your download location
    "download.prompt_for_download": False,
    "printing.default_destination_selection_rules": {
        "kind": "local",
        "namePattern": "Save as PDF",
    }
})
chrome_options.add_argument('--kiosk-printing')  # Enable silent printing

# Initialize the driver
driver = webdriver.Chrome(options=chrome_options)

# List of URLs to print (in this case, just one as an example)
urls = [
    "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html",
    # Add more URLs for the sections you want to download
]

for url in urls:
    driver.get(url)
    time.sleep(2)  # Give time for the page to load
    driver.execute_script('window.print();')  # Trigger print action
    time.sleep(3)  # Give time for the PDF to save

driver.quit()

