const controller = new ScrollMagic.Controller();
const timeline = new TimelineMax();

timeline
  .to(".front-img", 10, { y: -300 })
  .to(".main-title", 10, { y: 10 }, "-=10")
  .fromTo(".bg", { y: -15 }, { y: 0, duration: 10 }, "-=10")
  .to(".content", 10, { top: "0%" }, "-=10")
  .fromTo(".content-images", { opacity: 0 }, { opacity: 1, duration: 3 }, "-=7")
  .fromTo(".text", { opacity: 0 }, { opacity: 1, duration: 3 }, "-=4");

const scene = new ScrollMagic.Scene({
  triggerElement: "section",
  duration: "300%",
  triggerHook: 0,
})
  .setTween(timeline)
  .setPin("section")
  .addTo(controller);