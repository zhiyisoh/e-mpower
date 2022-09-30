<script setup>
import { onMounted } from "vue";
import AOS from "aos";
import Footer from "../components/Footer.vue";
import YearlySummary from "../components/yearlySummary.vue";

onMounted(() => {
  AOS.init();
})
</script>

<template>
  <div class="dashboard-view">

  <div class="bobo-chat" data-aos="fade-right">
      <h1>Hello, {{this.$store.state.auth.user.username}}!</h1>
      <p>I'm Bobo, E-MPOWER's resident recycling penguin. Let us review your recycling efforts this year as of today.
      </p>
    </div>
    <img src="/src/assets/penguinlogo.svg" alt="e-mpower logo" id="bobo-icon" data-aos="flip-left" data-aos-easing="ease-out-cubic"
    data-aos-duration="2000">

    <YearlySummary />
  </div>


  <Footer />
</template>
  
<style scoped>
  h1 {
    font-weight: 700;
}

.bobo-chat {
  width: 600px;
  text-align: left;
  padding: 9%;
  border-radius: 50%;
  background-color: white;
  margin-left: 2.6rem;
  margin-top: 2rem;
  filter: drop-shadow(10px 10px 4px #919196);
  display: inline-block;
  position: relative;
}

#bobo-icon {
  position: relative;
  display: inline-block;
  width: 300px;
  bottom: 40px;
  left: 80px;
}
</style>

  