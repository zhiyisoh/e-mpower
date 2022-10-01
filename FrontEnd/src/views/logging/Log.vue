<template>
  <div class="past-logs">
    <h1 class="log-title title">My Recycling Log</h1>
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
              <h5 class="card-title">R i c e</h5>
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
.past-logs {
  margin: 20px 0;
  padding: 7% 0%;
  border-radius: 0%;
  text-align: center;
}

.leaves-line {
  position: relative;
  width: 200px;
  right: 6px;
  top: 6px;
}

.log-title {
  margin-top: 0px;
}

.log-cards{
  margin: 20px 0;
  padding: 7% 0%;
}

.logs {
  margin: 20px ;
}

.card {
  text-align: center;
  background-color: #CEE5D0;
  border: 0;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

</style>