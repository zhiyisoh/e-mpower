<template>
    <div class="login">
      <h1>Hello {{this.$store.state.auth.user.username}}!</h1>
    </div>
  </template>
  
  <style>
  @media (min-width: 1024px) {
    .about {
      min-height: 100vh;
      display: flex;
      align-items: center;
    }
  }
  </style>

  