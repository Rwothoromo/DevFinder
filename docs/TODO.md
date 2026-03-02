# DevFinder Project Roadmap & Improvements

## 🚀 Mission & Impact
**DevFinder** aims to bridge the gender gap in technology by increasing the visibility of female and under-represented developers.
This project will serve as a platform to highlight talent, facilitate mentorship, and provide a direct path to the job market for underrepresented developers.

---

## 🎯 Strategic Goals

### 1. Gender Diversity & Visibility (Core Mission)
- [ ] **Gender-Based Filtering:** Implement logic to prioritize or filter for female/non-popular location developer profiles.
- [ ] **"Spotlight" Feature:** Create a rotating "Developer of the Week" section focusing on women in tech.
- [ ] **Skill Badges:** Add badges for specific certifications or achievements that are highly valued in the industry.
- [ ] **Mentorship Integration:** Add a "Open to Mentor" toggle on profiles to encourage community building.

### 2. Extra visibility targets
- [ ] **Impact Metrics:** Implement analytics to track how many users are discovering and reaching out to female developers through the app.
- [ ] **STEM Focus:** Add features that highlight developers working in STEM fields (Science, Technology, Engineering, Mathematics) to align with the foundation's mission.
- [ ] **Educational Resources:** Integrate a section for scholarships and grants available specifically for women in STEM.

---

## 🛠 Technical Improvements

### 🏗 Architecture & Code Quality
- [ ] **Migration to KSP:** Replace Kapt with Kotlin Symbol Processing for faster builds.
- [ ] **Koin Dependency Injection:** Implement Koin for lightweight, pragmatic dependency management (alternative to Hilt).
- [ ] **Paging 3 Library:** Use the Paging library to handle large lists of developers from APIs efficiently.
- [ ] **Unit & UI Test Coverage:** Reach 80%+ coverage using MockK and Compose UI Testing.

### 🔌 API Integrations
Beyond the GitHub API, explore these free public APIs for developer profiles:
- [ ] **Stack Overflow API (StackExchange):** Fetch reputation and top answers to showcase technical expertise.
- [ ] **Dev.to API:** Display articles and blog posts written by the developers.
- [ ] **GitLab API:** Include contributions from GitLab for a more holistic view of their work.
- [ ] **LinkedIn (Open Graph):** Explore limited public profile data for professional context.
- [ ] **Peerlist API:** Integration with professional networking platforms for designers and developers.

### 🎨 UI/UX Enhancements
- [ ] **Dark Mode Support:** Ensure full compatibility with Material 3 Dark Theme.
- [ ] **Accessibility (a11y):** Optimize for screen readers and ensure high contrast ratios.
- [ ] **Localization:** Support multiple languages to reach a global audience.
- [ ] **AI Profile Summaries:** Implement AI-powered summarization of developer profile descriptions to provide quick insights and improve discoverability.

---

## 📈 Next Steps
1. **Refactor Build Logic:** Move common versions to a Version Catalog (`libs.versions.toml`).
2. **Setup CI/CD:** Finalize the CircleCI pipeline for automated testing and deployment.
