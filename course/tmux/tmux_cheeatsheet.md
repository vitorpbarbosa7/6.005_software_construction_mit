Here’s a **cheat sheet of useful tmux commands** to help you get started and become more proficient:

---

### **Basic tmux Commands**
- `tmux`: Start a new tmux session.
- `tmux new -s session_name`: Start a new session with a name.
- `tmux attach`: Attach to the last active session.
- `tmux attach -t session_name`: Attach to a specific session.
- `tmux list-sessions`: List all active tmux sessions.
- `tmux kill-session -t session_name`: Kill a specific session.
- `tmux kill-server`: Kill all tmux sessions.

---

### **Prefix Key**
The **prefix key** is `C-b` (Ctrl + b) by default. Press it before any command to interact with tmux. For example, `C-b %` splits the pane horizontally.

---

### **Pane Management**
- `C-b %`: Split the current pane **vertically**.
- `C-b "`: Split the current pane **horizontally**.
- `C-b o`: Move to the **next pane**.
- `C-b ;`: Move to the **last active pane**.
- `C-b q`: Show pane numbers; press the number to switch to that pane.
- `C-b z`: Zoom in/out on the current pane.
- `C-b x`: Close the current pane.
- `C-b {`: Swap the current pane with the **previous pane**.
- `C-b }`: Swap the current pane with the **next pane**.
- `C-b Space`: Toggle between pane layouts.

---

### **Window Management**
- `C-b c`: Create a new window.
- `C-b n`: Switch to the **next window**.
- `C-b p`: Switch to the **previous window**.
- `C-b ,`: Rename the current window.
- `C-b w`: Show a list of windows to navigate.
- `C-b &`: Close the current window.

---

### **Session Management**
- `C-b d`: Detach from the current session.
- `C-b s`: List all sessions and switch between them.
- `tmux rename-session -t old_name new_name`: Rename a session.

---

### **Copy & Paste Mode**
- `C-b [`: Enter **copy mode** to scroll and select text.
  - **Navigation**:
    - Arrow keys or `k`/`j`: Move up/down.
    - `b`: Jump backward by a word.
    - `w`: Jump forward by a word.
    - `g`: Go to the top of the pane.
    - `G`: Go to the bottom of the pane.
  - **Selection**:
    - `Space`: Start selection.
    - `Enter`: Copy selection to the buffer.
- `C-b ]`: Paste from the buffer.

---

### **Resizing Panes**
- `C-b :resize-pane -L`: Resize pane **left**.
- `C-b :resize-pane -R`: Resize pane **right**.
- `C-b :resize-pane -U`: Resize pane **up**.
- `C-b :resize-pane -D`: Resize pane **down**.
- Shortcut resizing (if configured):
  - `C-b Alt-Arrow`: Resize panes using arrow keys.

---

### **Advanced Features**
- `C-b t`: Show the time.
- `C-b f`: Search for a window by name.
- `C-b m`: Mark the current pane.
- `C-b C-o`: Rotate panes in the current window.
- `C-b :`: Enter command mode to execute tmux commands (e.g., `kill-session`, `list-windows`).

---

### **Mouse Support**
If mouse support is enabled (`set -g mouse on` in your `.tmux.conf`):
- **Scroll with the mouse wheel** to navigate copy mode.
- Click to **switch panes or windows**.

---

### **Logging and Debugging**
- `tmux capture-pane -S -1000`: Capture the last 1000 lines in the current pane.
- `tmux save-buffer filename`: Save the capture buffer to a file.

---

### **Common Customizations in `.tmux.conf`**
Add these lines to your `.tmux.conf` for quality-of-life improvements:

```bash
# Enable mouse mode
set -g mouse on

# Use vi keys in copy mode
setw -g mode-keys vi

# Shorten delay for prefix commands
set -g escape-time 10
```

---

### **Quick Example Workflow**
1. Start tmux: `tmux`.
2. Create a new window: `C-b c`.
3. Split the pane horizontally: `C-b %`.
4. Split the pane vertically: `C-b "`.
5. Move between panes: `C-b o`.
6. Rename a window: `C-b ,` (type the new name and press Enter).
7. Detach: `C-b d`.
8. Reattach to the session: `tmux attach`.

---

### **Master tmux Commands**
Practice these commands to become proficient with tmux for multitasking and efficient terminal management. Let me know if you'd like help customizing your setup further!