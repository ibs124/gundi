const CLICK = "click";

main();

function main() {
   injectIcons();
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