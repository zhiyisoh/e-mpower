<script setup>
    import Footer from "../../components/Footer.vue";
    </script>

<template>
    <section class="profile">
        <h1>My Profile</h1>

        <img src="/src/assets/profilepic.svg" alt="leaves" id="profilepic">
        <div class="profile-details">
        <h4>Username: </h4>
        <p>{{this.$store.state.auth.user.username}}</p>
        
        <h4>Email: </h4>
        <p>{{this.$store.state.auth.user.email}}</p>
        <h4>Saved Bin Locations: </h4>
        <p>NULL</p>
        <h4>Password: </h4>
        <p>*********</p>
        <button type="button" class="btn btn-primary edit-btn "
        href="EditProfile.vue"><RouterLink to="/editprofile">Edit</RouterLink></button>
        </div>
        
        
        <Footer/>
    </section>
</template>

<style scoped>

    h1 {
        font-weight: 700;
    }

    h4 {
        font-family: 'Merriweather', sans-serif;
        color:#5E454B;
      }
    
    #profilepic {
        width: 250px;
        margin: 2%;
    }
    .profile {
    text-align: center;
    }

    .profile-details {
        margin: 20px auto;
        background-color: white;
        width: 500px;
        padding: 2% 5%;
        border-radius: 10%;
    }

    a:link{
        text-decoration: none;
        color: white;
    }
    
</style>