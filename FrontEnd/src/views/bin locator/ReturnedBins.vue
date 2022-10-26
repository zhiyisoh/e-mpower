<script setup>
    import Footer from "../../components/Footer.vue";
</script>

<template>
    <div class="locator-view">
        <div class="form-container">
            <div class="container">
                <h1>Nearest Bin</h1>
                <div class="row binentry">
                    <img src="/src/assets/recycle-bin.png" style="max-width: 200px">
                    <div class="item">
                    <h4>Address: </h4>
                    <h5>{{bins.address}}</h5>
                    <br><br>
                    <h4>Postal Code: </h4>
                    <h5>{{bins.postalCode}}</h5>
                    <br><br>
                    <h4>Accepts:</h4>
                    <h5 v-if = "bins.ict === true" > ICT </h5>
                    <h5 v-if = "bins.battery === true" >Batteries </h5>
                    <h5 v-if = "bins.lamp === true" > Bulbs </h5>
                    </div>
                </div>
                <RouterLink to="/binlocator">
                    <button class="btn btn-primary find-button" href="BinLocator.vue">Find Another Bin</button>
                </RouterLink>
                
            </div>
        </div>
    </div>
    <Footer/>
</template>

<script>
import axios from 'axios';

  export default {
    data(){
        return{
            bins : [],

        }
    }, created(){
        try{
            const API_URL = 'http://localhost:8080/api/bins/' + this.$route.params.binid;
            axios.get(API_URL, {
        headers: {
          'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken 
        }
      }).then(response =>
                this.bins = response.data
            );

        }catch(error){
            console.log(error);
        }
    }
  }
  </script>

<style scoped>

h1 {
    font-weight: 700;
}

h4 {
    display: inline;
}

h5 {
    display: inline;
}

label {
    margin: 7px 5px 0;
    font-size: 20px;
    
 
  }

    .binentry {
        padding-top: 60px;
    }
    .locator-view {
        text-align: center;
    }
    .container {
        width: 70%;
        margin: 30px auto;
        
    }

    .item{
        width: 55%;
        text-align: left;
    }
    .find-button {
        margin-left: auto;
        margin-right: 0;
        display: block;
    }
    a{
        text-decoration: none;
    }
    
</style>