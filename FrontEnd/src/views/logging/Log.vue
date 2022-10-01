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