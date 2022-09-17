<script setup>
  import FeatureSection from "../components/Feature.vue";
  import Footer from "../components/Footer.vue";
  </script>

<template>
  <div class="main-start-view">
    <div class="HeroSection">
      <img src="/src/assets/leaves.svg" alt="leaves" id="leaves-icon">
      <div class="main-motto">
        <h1 class="main-line">Your electronics deserve a second life.</h1>
        <p>Make your e-waste recycling journey fuss-free now!</p>
      </div>

      <div class="btn-sect">
        <RouterLink to="/login"><button type="button" class="btn btn-primary user-btn btn-lg">Login</button></RouterLink>
        <RouterLink to="/register"><button type="button" class="btn btn-outline-dark register-btn btn-lg" href="Register.vue">Register</button></RouterLink>
      </div>
    </div> 
    <img src="/src/assets/bin-logo.svg" alt="e-mpower logo" id="bin-logo">
  </div>

  <FeatureSection/>
  <Footer/>
</template>

<style>
  
  h1, p {
    font-family: 'Merriweather', sans-serif;
  }

  p {
    color: rgb(73, 73, 73);
  }

  /* -----------------------MAIN VIEW----------------------------------------- */
  .HeroSection {
    display: block;
    text-align: right;
    margin-top: 70px;
  }

  .main-line {
    font-size: 3.5rem;
    font-weight: 700;
  }

  #bin-logo {
    visibility: hidden;
  }

  #leaves-icon {
    position: absolute;
    left: -100px;
    top: 170px;
    width: 400px;
  } 

/* --------------------buttons-------------------- */
  .user-btn {
    margin-right: 20px;
  }

  .btn-primary {
    background-color: #5E454B !important;
    border-color: #5E454B !important;
  }

  .btn-outline-dark{
    color: #5E454B  !important;
  }

  .btn-outline-dark:hover {
    background-color: #5E454B !important;
    color: white !important;
  }

/* ----------------------------------viewport diff----------------------------------------------- */

@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }

  .btn-sect {
    margin-left: 90px;
  }

  .HeroSection {
    display: inline-block;
    width: 550px;
    margin: 130px 120px 0px;
    text-align: left;
  }

  #bin-logo {
    width: 330px;
    position: relative;
    bottom: 190px;
    left: -30px;
    visibility: visible;
  }

  #leaves-icon {
    left: -150px;
    top: 120px;
    width: 450px;
  }

  
  #ewaste-logo, #notes-logo{
    display: inline-block;
    position: relative;
  }

  .logging-desc, .ewaste-desc {
    margin-top: 40px;
    display: inline-block;
    text-align: left;
  }
}


</style>