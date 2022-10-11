<script setup>
    import Footer from "../../components/Footer.vue";
</script>
<template>
    <div class="indv-view">
        <div class="information">
            <h1>Item: {{data.itemName}}</h1>
            <p></p>
            <h5>Type: {{data.itemType}}</h5>
            <h5>You recycled this on the {{data.createdDate}}!</h5>
            <p></p>
            <h5>{{data.itemNotes}}</h5>
        </div>

        <!-- to insert image -->
        <img v-if="data.itemType === ('Lithium Ion Battery')" src="/src/assets/x-LI-image.svg"
            class="border border-5 border-light rounded" alt="hehe" id="profile-pic">
        <img v-if="data.itemType === ('Household Battery')" src="/src/assets/x-HB-image.svg"
            class="border border-5 border-light rounded" alt="hehe" id="profile-pic">
        <img v-if="data.itemType === ('Consumer EV Battery')" src="/src/assets/x-EVB-image.svg"
            class="border border-5 border-light rounded" alt="hehe" id="profile-pic">
        <img v-if="data.itemType === ('Consumer Lamp')" src="/src/assets/x-CL-image.svg"
            class="border border-5 border-light rounded" alt="hehe" id="profile-pic">
        <img v-if="data.itemType === ('ICT')" src="/src/assets/x-ICT-image.svg"
            class="border border-5 border-light rounded" alt="hehe" id="profile-pic">
        <img v-if="data.itemType === ('Electric Mobility')" src="/src/assets/x-EMD-image.svg"
            class="border border-5 border-light rounded" alt="hehe" id="profile-pic">
        <img v-if="data.itemType === ('Large Household Appliance')" src="/src/assets/x-LHA-image.svg"
            class="border border-5 border-light rounded" alt="hehe" id="profile-pic">
        <img v-if="data.itemType === ('Unregulated')" src="/src/assets/x-unregulated-image.svg"
            class="border border-5 border-light rounded" alt="hehe" id="profile-pic">

        <div class="btn-toolbar">
            <router-link :to="`/editlog/${this.$store.state.auth.user.id}/${this.$route.params.id}`" ><button type="button" class="btn btn-primary log-btn btn-lg"
                    href="EnterLog.vue">Edit</button></router-link>
            <button v-on:click="JSalert(this.$store.state.auth.user.id, this.$route.params.id, this.$store.state.auth.user.accessToken, this.$router)" type="button" class="btn btn-outline-dark log-btn btn-lg">Delete</button>
            <RouterLink to="/logging"><button type="button" class="btn btn-outline-dark back-btn btn-lg"
                    href="Log.vue">Back to Logs</button></RouterLink>
        </div>
    </div>
    <Footer/>
</template>

<script>
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
    name: 'onelog',
    data() {
        return {
            data: [],
        };
    },
    created() {
        const API_URL = 'http://localhost:8080/api/logging/';
        this.ewasteid = this.$route.params.id;
        axios.get(API_URL + this.$route.params.id).then(response =>
            this.data = response.data);
    }
}

function JSalert(userid, logid, accessToken, router){
	Swal.fire({
  title: 'Are you sure?',
  text: "You won't be able to revert this!",
  icon: 'warning',
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Yes, delete it!',
  preConfirm: () => {
            return axios
              .delete(`http://localhost:8080/api/logging/deletelog/${userid}/${logid}`,
            {
        headers: {
          'Authorization': 'Bearer ' + accessToken 
        }
      })
              .then(response => {
                if (response.status != 200) {
                  throw new Error("Something went wrong");
                }
                return router.push('/logging');;
              })
              .catch(error => {
                Swal.showValidationMessage(`Request failed: ${error}`);
              });
          },
          allowOutsideClick: () => !Swal.isLoading()
        
}).then((result) => {
  if (result.isConfirmed) {
    Swal.fire(
      'Deleted!',
      'Your log has been deleted.',
      'success'
    )
    
  }
})
}
</script>

<style>
.information {
    display: inline-block;
    width: 565px;
    margin: 10px 40px;
    text-align: left;
    position: relative;
}

.btn-toolbar {
    position: relative;
    bottom: 20px;
    right: 20px;
    left: 40px;
}

.log-btn {
    margin-right: 20px;
    display: inline-flex;
    align-items: center;
    text-decoration: none !important;
    
}

#profile-pic {
    position: relative;
    display: inline-block;
    width: 200px;
    top: 10%;
    left: 0%;
}

a{
    text-decoration: none;
}

</style>