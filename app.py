import requests
import sys

def main():
    resp = requests.get("https://httpbin.org/json")
    resp.raise_for_status()

    data = resp.json()
    title = data.get("slideshow",{}).get("title")
    if not title:
        print("Title not found", file=sys.stderr)
        sys.exit(1)
    print(title)

if __name__ == "__main__":
    main()