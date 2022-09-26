<template>
  <section class="feature-section container" data-aos="fade-up">
    <h1 class="title">Our Features</h1>
    <div class="features">
      <div class="bin-finding-feature">
        <img src="/src/assets/ewaste.svg" alt="ewaste" id="ewaste-logo">
        <div class="ewaste-desc">
          <h5 class="feature-title">Bin Locator</h5>
          <p >Having difficulty finding which are the nearby e-waste recycling bins which accept your
          type of e-waste?
          Our <strong>Bin Locator</strong> feature is the solution for you. Simply key in your location as well as
          select your e-waste category to view the top 5 nearest bins for you to recycle.</p>
        </div>
      </div>

      <div class="logging-feature ">
        <img src="/src/assets/notes.svg" alt="notes-logo" id="notes-logo">
        <div class="logging-desc">
          <h5 class="feature-title">Personalised Recycling Log</h5>
          <p>Want to find out how your e-waste recycling efforts have impacted our
            environment? Our unique <strong>Recycling Log</strong> feature will enable you to your recycling
            efforts
            and find out the amount of carbon dioxide emissions you have saved. An analytical summary of your recycling
            efforts as well as the recycling efforts of other users' will be exclusively available for you.</p>
          </div>
      </div>
    </div>
  </section>
</template>

<style>
/* ---------------------------feature section---------------------------------------- */


.feature-section {
  padding: 7% 15%;
  text-align: center;
  background-color: white;
  margin: 0;
  border-radius: 10%;
}

.title {
  font-weight: 700;

}

#ewaste-logo,
#notes-logo {
  width: 200px;
}

.bin-finding-feature,
.logging-feature {
  padding: 7% 5%;
}

.logging-desc,
.ewaste-desc {
  position: relative;
  top: 50px;
  margin: 20px auto;
  text-align: left;
  width: 50%;
}

.logging-desc{
  top: 75px;
  margin-bottom: 5%;
}


@media(min-width: 1024px) {

  #ewaste-logo,
  #notes-logo {
    display: inline-block;
    position: relative;
  }

  .logging-desc,
  .ewaste-desc {
    margin-left: 40px;
    display: inline-block;
  }

  .bin-finding-feature,
.logging-feature {
  padding: 1% 5%;
}

}
</style>

