<script setup>
    import Footer from "../../components/Footer.vue";
    import BinCard from "../../components/BinCard.vue";
</script>

<template>
    <!-- <div class="enter">
        <h1>Hello Admin, made a mistake somewhere?</h1>
            <img src="/src/assets/recycle-bin.gif" alt="leaves" class="bin-icon"> -->

        <div class="formBin">
            <Form @submit="onSubmit" :validation-schema="schema" v-on:submit.prevent="submitForm">
                <div class="form-group">
                    <label for="binAddress">Enter New Address</label>
                    <input name="binAddress" v-model="binAddress" type="text" class="form-control" />

                    <label for="postalCode">Enter New Postal code</label>
                    <input name="postalCode" v-model="postalCode" type="text" class="form-control" />

                    <label for="ICTRecycling">Recycles ICT?</label>
                    <input name="ICTRecycling" v-model="ICTRecycling" type="radio" id="one" value="One"  />
                    <label for="one">True</label>

                    <input name="ICTRecycling" v-model="ICTRecycling" type="radio" id="two" value="Two" />
                    <label for="two">False</label>
                    <br>

                    <label for="batteryRecycling">Recycles Battery?</label>
                    <input name="batteryRecycling" v-model="batteryRecycling" type="radio" id="one" value="One"  />
                    <label for="one">True</label>

                    <input name="batteryRecycling" v-model="batteryRecycling" type="radio" id="two" value="Two" />
                    <label for="two">False</label>
                    <br>

                    <label for="bulbRecycling">Recycles Bulbs?</label>
                    <input name="bulbRecycling" v-model="bulbRecycling" type="radio" id="one" value="One"  />
                    <label for="one">True</label>

                    <input name="bulbRecycling" v-model="bulbRecycling" type="radio" id="two" value="Two" />
                    <label for="two">False</label>
                </div>

                <div class="form-group">
                    <button class="btn btn-primary btn-block editbin-submit" :disabled="loading">
                        <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                        Submit
                    </button>

                </div>
            </Form>
        </div>
        <Footer/>
    <!-- </div> -->

</template>

<script>
// import axios from 'axios';


// export default {

//   name: 'EditBin',
//   el: "#app",
//   data() {
//     return {
//       address: "",
//       postal_code: 0,
//       //ict, batt, bulbs
//       ict: 0,
//       battery: 0,
//       bulb: 0,
//       record: []
//     }
//   }, methods: {
//     onSubmit(e) {
//       e.preventDefault();
//       if (!this.record.postal_code) {
//         alert('❌ Postal Code field is required ')
//         return
//       }

//       if (!this.record.address) {
//         alert('❌ Address field is required')
//         return
//       }

//       let currentObj = this;
//       let self = this;
//       const API_URL = 'http://localhost:8080/api/bins/updateBin/';

//       axios.put(API_URL + this.$store.state.auth.user.id + '/' + this.$route.params.id, {
//         address: this.record.address,
//         postal_code: this.record.postal_code,
//         ict: this.record.ict,
//         battery: this.record.battery,
//         bulb: this.record.battery
//       }, {
//         headers: {
//           'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
//         }
//       })
//         .then(function (response) {
//           currentObj.output = response.data;
//           history.back();
//         })
//         .catch(function (error) {
//           currentObj.output = error;
//           alert('Unsuccessful Submission. ' + error);
//         });
//     }
//   }, mounted() {
//     const url = "http://localhost:8080/api/getBin/"; //to be changed
//     axios.get(url + "671524", {
//       headers: {
//         'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
//       }
//     })
//       .then(response => {
//         this.record = response.data;
//         console.log(record)

//       }).catch((error) => {
//         this.error = "Error!  " + error;
//       });


//   }
// }

// </script>

<style scoped>
    h1 {
        font-weight: 700;
    }

    .container {
        text-align: center;
        position: relative;
    }

    .formBin {
        width: 800px;
        margin: 0 auto;
    }

    label {
    margin: 7px 5px 0;
    font-size: 20px;
  }

  .editbin-submit{
    margin: 20px;
  }

</style>