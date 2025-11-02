const CLICK = "click";
const APP = ".app";
const APP_SIDEBAR_EXPANDED = "app--sidebar-expanded";

main();

function main() {
    injectIcons();

    setDOMContentLoadedEventListener()

    setSidebarToggleBtn();

    setDropdownButtons();

    setClassToggleButtonFor("app--dark-mode", APP, ".app__dark-mode-toggle");
}

function setDOMContentLoadedEventListener() {
    window.addEventListener("DOMContentLoaded", () => {
        updateSidebar(document.querySelector(APP));
    });
}

function setDropdownButtons() {
    const buttons = document.querySelectorAll(".dropdown__btn");
    const active = "dropdown--active";
    const latched = "dropdown--latched";

    buttons.forEach(btn => btn.addEventListener(CLICK, () => {
        const focusedElement = btn.parentElement;

        focusedElement.classList.toggle(active)

        getSiblings(focusedElement)
            .filter(sibling => !sibling.classList.contains(latched))
            .forEach(sibling => sibling.classList.remove(active));
    }));
}

function setSidebarToggleBtn() {
    const buttons = document.querySelectorAll(".app__sidebar-toggle");
    const app = document.querySelector(APP);

    buttons.forEach(btn => btn.addEventListener(CLICK, () => {
        app.classList.toggle(APP_SIDEBAR_EXPANDED);
        updateSidebar(app);
    }))
}

function updateSidebar(app) {
    const classes = document.querySelector(".sidebar").classList;
    const collapsed = "sidebar--collapsed";
    const expanded = "sidebar--expanded";

    if (app.classList.contains(APP_SIDEBAR_EXPANDED)) {
        classes.add(expanded);
        classes.remove(collapsed);
        return;
    }

    classes.add(collapsed);
    classes.remove(expanded);
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

function setClassToggleButtonFor(clazz, hostElemetSelector, buttonSelector) {
    document.querySelectorAll(buttonSelector)
        .forEach(button => button.addEventListener(CLICK, () =>
            toggleClassForSelector(clazz, hostElemetSelector)))
}

function toggleClassForSelector(clazz, hostElemetSelector) {
    document.querySelectorAll(hostElemetSelector).forEach(x => x.classList.toggle(clazz));
}

function getSiblings(element) {
    return Array.from(element.parentNode.children).filter(child => child !== element);
}