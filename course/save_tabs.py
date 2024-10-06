import requests
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import time

# Fetch the list of open tabs from Chrome's DevTools
def get_open_tabs():
    response = requests.get('http://localhost:9222/json')
    tabs = response.json()
    return tabs

# Setup Chrome WebDriver with headless mode
def setup_driver():
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument("--disable-gpu")
    chrome_options.add_argument("--no-sandbox")
    chrome_options.add_argument("--print-to-pdf")
    driver = webdriver.Chrome(options=chrome_options)
    return driver

# Save the URL as PDF
def save_as_pdf(driver, url, output_path):
    driver.get(url)
    # Add some sleep to make sure the page loads completely
    time.sleep(3)
    # Command to print the page as a PDF
    driver.execute_cdp_cmd("Page.printToPDF", {'path': output_path})

# Main workflow
if __name__ == "__main__":
    driver = setup_driver()
    tabs = get_open_tabs()
    
    # Save each tab as a PDF
    for i, tab in enumerate(tabs):
        url = tab['url']
        print(f"Saving {url} as PDF...")
        output_path = f"tab_{i+1}.pdf"
        save_as_pdf(driver, url, output_path)
    
    driver.quit()

