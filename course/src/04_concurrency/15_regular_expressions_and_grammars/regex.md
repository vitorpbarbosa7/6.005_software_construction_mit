To write a **regular expression** for this grammar in **VS Code** and search for matching patterns inside the text, you'll need to translate the grammar into a regex format. Here's how you can do it step by step:

---

### **1. Understand the Grammar Components**
The grammar you provided specifies URLs in a structured format:
- **`url ::= 'http://' hostname (':' port)? '/'`**
  - Starts with `http://`.
  - Followed by a **hostname**.
  - Optionally includes a **port** (preceded by a `:`).
  - Ends with a `/`.

- **`hostname ::= word '.' hostname | word '.' word`**
  - A hostname is hierarchical, with one or more `word`s separated by `.`.

- **`port ::= [0-9]+`**
  - The port is one or more digits.

- **`word ::= [a-z]+`**
  - A word is one or more lowercase letters.

---

### **2. Translate Grammar into Regex**
We need to capture the hierarchical structure and optional components in regex. Here’s the translation:

#### **Regex for `url`**
```regex
http://[a-z]+(\.[a-z]+)+(:[0-9]+)?/
```

#### **Explanation**:
1. **`http://`**:
   - Matches the literal string `http://`.

2. **`[a-z]+`**:
   - Matches the first `word` in the hostname.

3. **`(\.[a-z]+)+`**:
   - Matches one or more additional `word`s, separated by `.`.
   - Example: `.com`, `.example.com`.

4. **`(:[0-9]+)?`**:
   - Matches an optional `:` followed by one or more digits (the port).

5. **`/`**:
   - Matches the final `/`.

---

### **3. Using the Regex in VS Code**
1. Open **Find** in VS Code:
   - Press `Ctrl+F` (or `Cmd+F` on macOS).
2. Enable **Regex Mode**:
   - Click the `.*` button in the search bar.
3. Paste the Regex:
   ```regex
   http://[a-z]+(\.[a-z]+)+(:[0-9]+)?/
   ```
4. Search:
   - This will highlight all URLs in your file that match the grammar.

---

### **4. Testing Examples**
#### Matching Text:
- `http://example.com/`
- `http://sub.example.com:8080/`
- `http://abc.def.ghi:1234/`

#### Non-Matching Text:
- `https://example.com/` (missing `http://`)
- `http://example/` (missing `.com` or a valid hostname structure)
- `http://example.com` (missing the final `/`)

---

### **5. Optional Enhancements**
If you want to refine the regex to allow:
- **Uppercase letters in `word`**:
  Replace `[a-z]` with `[a-zA-Z]`.
- **`https` URLs**:
  Change `http://` to `(http|https)://`.

Updated Regex:
```regex
(http|https)://[a-zA-Z]+(\.[a-zA-Z]+)+(:[0-9]+)?/
```

---

This regex will work in VS Code’s search bar and match text URLs based on your grammar!
