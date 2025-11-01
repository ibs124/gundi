const CLICK = "click";
const APP = ".app";
const APP_SIDEBAR_EXPANDED = "app--sidebar-expanded";

main();

function main() {
    injectIcons();
    setSidebarToggleBtn();
}

function setSidebarToggleBtn() {
    const app = document.querySelector(APP);
    const buttons = document.querySelectorAll(".app__sidebar-toggle");

    buttons.forEach(btn =>
        btn.addEventListener(CLICK, () => app.classList.toggle(APP_SIDEBAR_EXPANDED)));
}

function injectIcons() {
    fetch("../assets/icons.svg")
        .then(res => res.text())
        .then(data => {
            const div = document.createElement("div");
            div.id = "icons";
            div.style.display = "none";
            div.innerHTML = data;
            document.body.insertAdjacentElement("afterbegin", div);
        })
        .catch(err => {
            console.error("Failed to load icons.svg:", err);
        });
}
