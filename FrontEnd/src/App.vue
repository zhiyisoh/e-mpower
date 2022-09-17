<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <div id="app" class="wrapper">
      <nav class="navbar navbar-expand-lg navbar-light bg-transparent">
          <div class="container">
              <a class="navbar-brand" href="/"><img src="/src/assets/e-mp.png" alt="e-mpower logo" id="main-logo"></a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
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
      </nav>
  </div>
  </header>
  <RouterView />
</template>

<style scoped>
  #main-logo{
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
