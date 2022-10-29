<script setup>
    import Footer from "../../components/Footer.vue";
    </script>

<template>
    <section class="profile">
        <h1>My Profile</h1>

        <img src="/src/assets/profilepic.svg" alt="leaves" id="profilepic">
        <div class="profile-details">
        <h4>Username: </h4>
        <p>{{record.username}}</p>
        
        <h4>Email: </h4>
        <p>{{record.email}}</p>
        <h4>Password: </h4>
        <p>*********</p>
        <button type="button" class="btn btn-primary edit-btn "
        href="EditProfile.vue"><RouterLink to="/editprofile">Edit</RouterLink></button>
        </div>
        
        
        <Footer/>
    </section>
</template>

<script>
import axios from 'axios';


export default {

  name: 'Profile',
  el: "#app",
  data() {
    return {
      record: []
    }
  }, created() {
    const url = "http://localhost:8080/api/auth/profile/" ;
    axios.get(url + this.$store.state.auth.user.id, {
      headers: {
        'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
      }
    })
      .then(response => {
        this.record = response.data;
        console.log(response.data);

      }).catch((error) => {
        this.error = "Error!  " + error;
      });

  }
}

</script>

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