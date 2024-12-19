document.addEventListener("DOMContentLoaded", function () {
    console.log("homeScript.js loaded!");

    // Theme Toggle Functionality
    const themeToggleButton = document.querySelector(".theme-toggle-button");
    const body = document.body;

    // Toggle between light and dark theme
    themeToggleButton.addEventListener("click", function () {
        body.classList.toggle("dark-theme");

        // Update the button icon
        if (body.classList.contains("dark-theme")) {
            themeToggleButton.textContent = "🌙";
            localStorage.setItem("theme", "dark");
        } else {
            themeToggleButton.textContent = "☀️";
            localStorage.setItem("theme", "light");
        }
    });

    // Load saved theme from localStorage
    if (localStorage.getItem("theme") === "dark") {
        body.classList.add("dark-theme");
        themeToggleButton.textContent = "🌙";
    } else {
        themeToggleButton.textContent = "☀️";
    }

    // Live Search Filtering (Optional Enhancement)
    const searchInput = document.querySelector("input[name='search']");
    const productListItems = document.querySelectorAll(".product-list li");

    if (searchInput) {
        searchInput.addEventListener("input", function () {
            const searchValue = searchInput.value.toLowerCase();

            productListItems.forEach(item => {
                const productName = item.querySelector("strong").textContent.toLowerCase();

                if (productName.includes(searchValue)) {
                    item.style.display = "block";
                } else {
                    item.style.display = "none";
                }
            });
        });
    }

    console.log("Theme and search functionality loaded.");
});
