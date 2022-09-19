<script setup>
import Footer from "../components/Footer.vue";
</script>

<template>
  <div class="About">

    <div class="about-section">
      <h1 class="aboutus-title title">About Us.</h1>
      <img src="/src/assets/leave-line.svg" alt="leaves" class="leaves-line">

      <p class="sec-desc  aboutus-description">
        E-MPOWER is a web application which allows our members to smoothen their e-waste recycling process
        and view the impact of individual and collective recycling efforts.
        <br><br>
        Our team was formed in for our project in CS203 Collaborative Software Development module.
        Our goal is to implement a site which will help the public to make their recycling process
        more convenient and engaging. During our research, we noticed the lack of awareness and knowledge
        of the proper processes of e-waste management. As a result, we decided to build E-MPOWER to tackle this
        prevalent
        issue.
        Within these 3 months, we were able to successfully deploy E-MPOWER for users to enjoy
        our features and hope that our members will benefit from them.
      </p>

      <img src="/src/assets/recycle-bin.gif" alt="recycling-bin" id="recycle-bin-icon">
    </div>


    <div class="team-section">
      <h1 class="aboutus-title title">The Team.</h1>
      <img src="/src/assets/leave-line.svg" alt="leaves" class="leaves-line">

      <div class="row row-cols-1 row-cols-md-3 g-4 profile-cards">
        <div class="col">
          <div class="card h-100">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Chloe Koh</h5>
            </div>
          </div>
        </div>

        <div class="col">
          <div class="card h-100">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Soh Zhi Yi</h5>
            </div>
          </div>
        </div>

        <div class="col">
          <div class="card h-100">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Jesleen</h5>
            </div>
          </div>
        </div>

        <div class="col">
          <div class="card h-100">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Kwek Zhi Yi</h5>
            </div>
          </div>
        </div>

        <div class="col">
          <div class="card h-100">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Jess Toh</h5>
            </div>
          </div>
        </div>

        <div class="col">
          <div class="card h-100">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Abiya Immaculate</h5>
            </div>
          </div>
        </div>
      </div>
    </div>


    <div class="thankyou-section">
      <h1 class="aboutus-title title">Special Thanks & Acknowledgements.</h1>
      <img src="/src/assets/leave-line.svg" alt="leaves" class="leaves-line">

      <p class="sec-desc"> Sample text: We would like to express our thanks to our CS203 Professors and Instructors for
        the guidance and lessons, and
        OneMap API for making this application possible.
      </p>
    </div>

  </div>

  <Footer />
</template>

<style>
.About {
  padding: 7% 15%;
  text-align: center;
}

.about-section {
  background-color: white;
  margin: 20px 0;
  padding: 7% 15%;
  border-radius: 10%;
}

.aboutus-title {
  margin-top: 20px;
}

.leaves-line {
  position: relative;
  width: 200px;
  right: 6px;
  top: 6px;
}

#recycle-bin-icon {
  width: 200px;
}


/* --------------TEAM SECTION--------------------- */
.team-section {
  padding: 7% 0%;
}

.profile-cards {
  margin: 30px 0;
}

.card {
  background-color: #CEE5D0;
  border: 0;
}

.card-title {
  text-align: center;
}

.sec-desc {
  margin-top: 10px;
  padding: 7% 0;
}

/* ACK */

.thankyou-section {
  background-color: white;
  margin: 20px 0;
  padding: 7% 15%;
  border-radius: 10%;
}
</style>