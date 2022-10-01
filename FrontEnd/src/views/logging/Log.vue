<script>
</script>

<template>
    <!-- <h1>My Log </h1> -->
  <div class="past-logs">
    <h1 class="logs-title title">My Recycling Log</h1>
    <img src="/src/assets/leave-line.svg" alt="leaves" class="leaves-line">
  
    <div class = "row row-cols-1 row-cols-md-4 g-4 log-cards">
        <div class = "col">
          <div class="card h-100">
            <div class="card-body">
              <h5 class="card-title">Television</h5>
              <RouterLink to="/singlelog"><button type="button" class="btn btn-outline-dark reg-btn btn-lg" href="LogPage.vue">More</button></RouterLink>
            </div>
          </div>
        </div>
        
        <div class = "col">
          <div class="card h-100">
            <div class="card-body">
              <h5 class="card-title">R i c e cooker</h5>
              <RouterLink to="/singlelog"><button type="button" class="btn btn-outline-dark reg-btn btn-lg" href="LogPage.vue">More</button></RouterLink>
            </div>
          </div>
        </div>  

        <div class = "col">
          <div class="card h-100">
            <div class="card-body">
              <h5 class="card-title">Banananya</h5>
              <RouterLink to="/singlelog"><button type="button" class="btn btn-outline-dark reg-btn btn-lg" href="LogPage.vue">More</button></RouterLink>
            </div>
          </div>
        </div>

        <div class = "col">
          <div class="card h-100">
            <div class="card-body">
              <h5 class="card-title">Bottle</h5>
              <RouterLink to="/singlelog"><button type="button" class="btn btn-outline-dark reg-btn btn-lg" href="LogPage.vue">More</button></RouterLink>
            </div>
          </div>
        </div>

    </div>

    </div>  

</template>

<style>
  .leaves-line {
  position: relative;
  width: 200px;
  right: 6px;
  top: 6px;
  }

  .past-logs {
  margin-top: 20px;
  text-align: center;
  margin: 20px 0;
  padding: 7% 15%;
  border-radius: 10%;
  }

  .log-cards {
  margin: 30px 0;
  }

  .card {
  background-color: #CEE5D0;
  border: 0;
  }

  .card-title {
  text-align: center;
  }
</style>