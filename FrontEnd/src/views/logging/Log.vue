<script setup>
    import Footer from "../components/Footer.vue";
    </script>

<template>
    <h1>My Log </h1>

    <div class="past-logs">
        <div class="row row-cols-1 row-cols-md-3 g-4 logs">

        <div class="log-entry">
            <div class="card h-100">
                <div class="card-body">
                <h5 class="card-title">Bottle</h5>
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
  margin: 30px 0;
}

.card {
  background-color: #CEE5D0;
  border: 0;
}

</style>