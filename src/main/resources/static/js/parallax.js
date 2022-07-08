let controller = new ScrollMagic.Controller();
let timeline = new TimelineMax();

timeline
  .to(".front-img", 10, { y: -300 })
  .to(".main-title", 10, { y: 10 }, "-=10")
  .fromTo(".bg", { y: 0 }, { y: 0, duration: 10 }, "-=10")
  .to(".page-wrapper", 10, { top: "0%" }, "-=10")
  .fromTo(".content", { opacity: 0 }, { opacity: 1, duration: 3 }, "-=7")
  // .fromTo(".text", { opacity: 0 }, { opacity: 1, duration: 3 }, "-=4");

let scene = new ScrollMagic.Scene({
  triggerElement: ".main",
  duration: "300%",
  triggerHook: 0,
})
  .setTween(timeline)
  .setPin(".main")
  .addTo(controller);