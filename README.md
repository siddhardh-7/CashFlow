# [CashFlow](https://github.com/siddhardh-7/CashFlow)
##### Smart Expense Tracking for Smart Business Owners


## 📌 App Overview
Smart Daily Expense Tracker is a full-featured module built for small business owners to easily record, analyze, and manage their daily expenses.  
It provides a clean and simple UI with screens for **Expense Entry**, **Expense List**, and **Expense Reports**, helping users digitize expense records instead of losing them on paper or chat apps.  

---

## 🤖 AI Usage Summary
Throughout the development, I used **AI tools (ChatGPT, Copilot, Cursor)** to accelerate the workflow.  
AI helped me in:
- Generating Jetpack Compose UI layouts and optimizing state management with `ViewModel + StateFlow`.  
- Structuring the MVVM architecture and writing boilerplate ViewModel/Repository code faster.  
- Prompting for UX/UI enhancements like empty states, validations, and animations.  
- Creating clear documentation (this README) and refining code comments.  

AI was not just supportive but **actively co-pilot in design + implementation decisions**.

---

## 💬 Prompt Logs
Here are some of the **key prompts I used** (unedited / raw) while building the project:

- "give me a clean MVVM structure for expense tracker in jetpack compose with stateflow and repository"  
- "how to show total spent today at the top of screen in realtime jetpack compose"  
- "jetpack compose expense entry screen with title, amount, category dropdown, notes, image upload and submit button"  
- "add animation when expense is added and show toast message"  
- "how to implement expense list screen grouped by category or time with toggle button"  
- "can you show me mocked report screen with bar chart for last 7 days in compose"  
- "simulate pdf or csv export and trigger share intent compose"  
- "give me idea for theme switcher dark and light mode in jetpack compose"  
- "how to add validation like amount > 0 and title should not be empty in viewmodel"  
- "mock duplicate detection when adding same expense again"  
- "navigation setup between 3 screens in jetpack compose cleanest way"  
- "write code comments for better readability"  
- "help me write impactful readme file for smart daily expense tracker module"  

---

## ✅ Checklist of Features Implemented
- [x] Expense Entry Screen with:
  - Title, Amount, Category, Notes, Receipt Image
  - Realtime "Total Spent Today"
  - Submit with animation + Toast
- [x] Expense List Screen:
  - View Today’s + Previous dates (filter/calendar)
  - Group toggle (Category / Time)
  - Show totals + empty state
- [x] Expense Report Screen:
  - Mock last 7 days report
  - Daily totals, category totals
  - Bar chart (mocked)
- [x] Navigation between all screens
- [x] In-memory Repository + ViewModel with StateFlow
- [x] Bonus:
  - Theme switcher (Light/Dark)
  - Room database
  - Basic validation (amount > 0, title non-empty)
  - Duplicate detection (mock)
  - Simple animations
  - Export: Simulate PDF/CSV export

## APK Download Link
[CashFlow APK](https://drive.google.com/file/d/17zTDZSs9Pr6cE4YqAfeAm8JoX9QGSRqq/view?usp=drive_link)


## Screenshots

### Light Mode:
<p align="center">
<img width="33%" alt="entry" src="https://github.com/user-attachments/assets/02aa3294-57c1-4c3c-a20f-2783b3aa9858" />
<img width="33%" alt="report" src="https://github.com/user-attachments/assets/89489f53-df40-45e8-8305-0eb0170c93d0" />
<img width="33%" alt="list" src="https://github.com/user-attachments/assets/95fa7624-8b78-4848-a6d8-642e934ed3b0" />
</p>

### Dark Mode:
<p align="center">
  <img width="33%" alt="image" src="https://github.com/user-attachments/assets/1480ebdf-2141-4b79-9db2-74a30a86eb73" />
  <img width="335" alt="image" src="https://github.com/user-attachments/assets/881e754d-88c8-4e25-9162-f2a030a742bf" />
  <img width="33%" alt="image" src="https://github.com/user-attachments/assets/57a5ca53-f088-4c78-a6c7-a3aa1d476e9d" />
</p>
