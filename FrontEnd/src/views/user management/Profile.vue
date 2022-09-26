<template>
    <h1>My Profile</h1>
    <h4>Username: {{this.$store.state.auth.user.username}}</h4>
    <h4>Email: {{this.$store.state.auth.user.email}}</h4>
    <h4>Saved Bin Locations: Only can store up to 3</h4>
    <h4>Change Password?</h4>
</template>