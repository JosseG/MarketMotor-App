function setac(){
  console.log("holafdsjfs")
    var accordElement = document.getElementsByClassName("accordion");
        
for (i = 0; i < accordElement.length; i++) {
  accordElement[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.display === "initial") {
      panel.style.display = "none";
    } else {
      panel.style.display = "initial";
    }
  });
}
}