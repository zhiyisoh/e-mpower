<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <div id="app" class="wrapper">
      <nav class="navbar navbar-expand-lg navbar-light bg-transparent">
        <div class="container">
          <div v-if="!currentUser">
            <a class="navbar-brand" href="/"><img src="/src/assets/e-mp.png" alt="e-mpower logo" id="main-logo"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
              <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                  <RouterLink to="/" class="nav-link">Home</RouterLink>
                </li>
                <li class="nav-item">
                  <RouterLink to="/about" class="nav-link">About Us</RouterLink>
                </li>
                <li class="nav-item">
                  <RouterLink to="/login" class="nav-link">Login</RouterLink>
                </li>
                <li class="nav-item">
                  <RouterLink to="/register" class="nav-link">Register</RouterLink>
                </li>
              </ul>
            </div>
          </div>

          <div v-if="currentUser">
            <a class="navbar-brand" href="/home"><img src="/src/assets/e-mp.png" alt="e-mpower logo" id="main-logo"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02"
              aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
              <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                  <RouterLink to="/home" class="nav-link">My Home</RouterLink>
                </li>
                <li class="nav-item">
                  <RouterLink to="/about" class="nav-link">About Us</RouterLink>
                </li>
                <li class="nav-item">
                  <RouterLink to="/binlocator" class="nav-link">Bin Locator</RouterLink>
                </li>
                <li class="nav-item">
                  <RouterLink to="/logging" class="nav-link">My Recycling Log</RouterLink>
                </li>
                <li class="nav-item">
                  <RouterLink to="/profile" class="nav-link">Profile</RouterLink>
                </li>
                <li>
                  <button class=" logout-btn btn btn-primary nav-link" @click.prevent="logOut">
                    Log Out
                  </button>
                </li>
              </ul>
              
            </div>
            
          </div>

        </div>
      </nav>
    </div>
  </header>
  <RouterView />
</template>

<style scoped>
#main-logo {
  width: 170px;
  position: relative;
  left: 0px;
  bottom: 5px;
}

.navbar-nav > li{
  padding-left:30px;
  padding-right:30px;
}


header {
  line-height: 1.5;
  max-height: 100vh;
}

nav {
  font-size: 1rem;
  padding: 1rem 0;
  margin: 1rem 0 1rem;
  color: #5E454B;
}

.container {
  width: 100%;
}
.logout-btn {
  color: white !important; 
  padding: 4%;
}

.navbar-toggler {
  position: absolute;
  right: 25px;
}


@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
  }
  
  .logout-btn{
    margin-left: 10px;
    padding: 5%;
  }
}

@media (min-width: 992px){
.navbar-expand-lg .navbar-collapse {
    display: inline-block !important;
    flex-basis: auto;
}
}

.navbar-expand-lg .navbar-collapse .collapse{
  display: inline-block !important;
  /* flex-basis: auto; */
}
</style>


<script>
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>
  