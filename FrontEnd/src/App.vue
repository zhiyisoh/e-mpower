<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <div id="app" class="wrapper">
      <nav class="navbar navbar-expand-lg navbar-light bg-transparent">
        <div class="container">

          <div v-if="!currentUser" class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <a class="navbar-brand" href="/"><img src="/src/assets/e-mp.png" alt="e-mpower logo" id="main-logo"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02"
              aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
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

          <div v-if="currentUser" class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <a class="navbar-brand" href="/home"><img src="/src/assets/e-mp.png" alt="e-mpower logo" id="main-logo"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02"
              aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
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
                <RouterLink to="/logging" class="nav-link">My Log</RouterLink>
              </li>
              <li class="nav-item">
                <RouterLink to="/profile" class="nav-link">Profile</RouterLink>
              </li>
              <li class="nav-item">
                <a class="nav-link" @click.prevent="logOut">
                  LogOut
                </a>
              </li>
            </ul>
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
  bottom: 5px;
}

header {
  line-height: 1.5;
  max-height: 100vh;
}

nav {
  font-size: 1rem;
  padding: 1rem 0;
  margin-top: 1rem;
  color: #5E454B;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
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
  