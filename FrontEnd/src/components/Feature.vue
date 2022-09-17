<template>
    <section class="feature-section container">
    <h1 class="title">Our Features</h1>
    <div class="features">
      <div class="bin-finding-feature">
        <img src="/src/assets/ewaste.svg" alt="ewaste" id="ewaste-logo">
        <p class="ewaste-desc ">Having difficulty finding which are the nearby e-waste recycling bins which accept your type of e-waste? 
          Our <strong>Bin Locator</strong> feature is the solution for you. Simply key in your location as well as select your e-waste category to view the top 5 nearest bins for you to recycle.</p>
      </div>

      <div class="logging-feature ">
        <img src="/src/assets/notes.svg" alt="notes-logo" id="notes-logo">
        <p class="logging-desc">Want to find out how your e-waste recycling efforts have impacted our environment? The <strong>Recycling Log</strong> feature will enable you to record your recycling efforts 
          and find out the amount of carbon dioxide emmissions you have saved! An analytical summary of your recycling efforts as well as the recycling efforts of other users' will be exclusive to you!</p>
      </div>
    </div>
  </section>
</template>

<style>
    /* ---------------------------feature section---------------------------------------- */
.feature-section{
    padding: 7% 15%;
    text-align: center;
    background-color: white;
    position: relative;
    margin: 0;
    border-radius: 25% 25% 10% 10%;
  }

  .title{
    font-weight: 700;
  }

  #ewaste-logo, #notes-logo{
    width: 200px;
  }
  

  .bin-finding-feature, .logging-feature{
    margin-top: 50px;
  }

  .logging-desc, .ewaste-desc {
    position: relative;
    top: 30px;
    margin-top: 40px;
    margin-left: 30px;
    text-align: left;
    width: 550px;
  }

  .logging-desc {
    top: 50px;
  }

  @media(min-width: 1024px){
    #ewaste-logo, #notes-logo{
    display: inline-block;
    position: relative;
  }

  .logging-desc, .ewaste-desc {
    margin-top: 40px;
    display: inline-block;
  }
  }
</style>

