<!-- <script setup>
import Footer from "../components/Footer.vue";
</script> -->

<template>
    <h1>My Log </h1>

    <div class="past-logs">
        <div class="row row-cols-1 row-cols-md-3 g-4 logs">

        <div class="log-entry">
            <div class="card">
                <div class="card-body">
                <h5 class="card-title">battery</h5>
                </div>
            </div>
        </div>
        <div class="log-entry">
            <div class="card">
                <div class="card-body">
                <h5 class="card-title">charger</h5>
                </div>
            </div>
        </div>

        <div class="log-entry">
            <div class="card">
                <div class="card-body">
                <h5 class="card-title">charger</h5>
                </div>
            </div>
        </div>

        <div class="log-entry">
            <div class="card">
                <img src="img_avatar.png" alt="the picture" style="width:50%">
                <div class="card-body">
                <h5 class="card-title">charger</h5>
                </div>
            </div>
        </div>

        </div>

    </div>

</template>

<style>
.past-logs {
  padding: 7% 0%;
}

.logs {
  margin: 40px ;
}

.card {
  background-color: #ffffff;
  border: 0;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.log-entry {
  padding: 2px 16px;
}

</style>