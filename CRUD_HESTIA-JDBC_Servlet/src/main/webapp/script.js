const loader = document.getElementById("preloader");
const closeForm = document.getElementById("close-form");
const formContainer = document.getElementById("form-container");
const signUp = document.getElementById("sign-up");

let centerIndex = 2;
const previous = document.getElementById("previous");
const next = document.getElementById("next");

var path = window.location.pathname;
var page = path.split("/").pop();

if (page === "") {
  const advantagesStack = Array.from(document.querySelectorAll(".advantage"));

  const updateClasses = () => {
    advantagesStack.forEach((advantage, index) => {
      const position = Math.abs(index - centerIndex);
      advantage.classList.toggle("main", position === 0);
      advantage.classList.toggle("secondary", position === 1);
      advantage.classList.toggle("hidden", position > 1);
    });
  };

  const shiftCarousel = (direction) => {
    let item;
    if (direction === "previous") {
      item = advantagesStack.pop(); // Remove the last item
      advantagesStack.unshift(item); // Add it to the front
    } else {
      item = advantagesStack.shift(); // Remove the first item
      advantagesStack.push(item); // Add it to the back
    }
    
    updateClasses();
  };

  previous.addEventListener("click", () => shiftCarousel("previous"));
  next.addEventListener("click", () => shiftCarousel("next"));
}

signUp.addEventListener("click", () => {
  formContainer.classList.remove("closed-form");
  document.body.classList.remove("scrolling-allowed");
});

closeForm.addEventListener("click", () => {
  formContainer.classList.add("closed-form");
  document.body.classList.add("scrolling-allowed");
  console.log(page)
});

window.addEventListener("load", function () {
  loader.classList.add("loaded");
  document.body.classList.add("scrolling-allowed");
});

function redirectToAdminLogin() {
  document.getElementById("login-form").action = "login-admin.jsp";
}

function redirectToSecretAreaLogin() {
  document.getElementById("login-form").action = "https://hestia-secrect-area-vercel.vercel.app";
}