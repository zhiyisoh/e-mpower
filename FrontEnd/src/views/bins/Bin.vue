<script setup>
import Footer from "../../components/Footer.vue";
</script>

<template>
  <div class="container">
    <h1>Recycling Bins</h1>
    <div class="content">
      <div class="searchBin">
        <h3>Search Bin to modify</h3>
        <div>
          <Form @submit="onSubmit" :validation-schema="schema" v-on:submit.prevent="submitForm">
            <div class="form-group">
              <label for="postalCode">Bin Postal Code/Address</label>
              <input name="postalCode" v-model="postalCode" type="text" class="form-control" />
            </div>

            <div class="form-group">
              <button class="submit-btn btn btn-primary btn-block" :disabled="loading">
                <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                Submit
              </button>
            </div>
          </Form>
        </div>
      </div>

      <div class="addBin">
        <h3>or Add a new Bin here</h3>
        <br>
        <RouterLink to="/enterbin"><button type="button" class="btn btn-outline-dark back-btn btn-lg"
          >Add New Bin</button></RouterLink>
      </div>
    </div>

  </div>


  <Footer />
</template>

<script>
import axios from 'axios';

export default {
  name: 'BinEntry',
  data() {
    return {
      postalCode: ''
    }
  }, methods: {
    onSubmit(e) {
      e.preventDefault();

      if (!(this.postalCode)) {
        alert('❌ Address field is required')
        return
      }
      //console.log(this.$store.state.auth.user.accessToken);
      let currentObj = this;
      let self = this;

      const API_URL = 'http://localhost:8080/api/bins/getBin/';
      axios.get(API_URL + this.postalCode, {
        postalCode: this.postalCode
      }, {
        headers: {
          'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
        }
      })
        .then(function (response) {
          currentObj.output = response.data;
          console.log(response.data);
          const array = [];
          for (let i = 0; i < response.data.length; i++) {
            array[i] = response.data[i].id;
          }
          self.$router.push({ path: '/BinResults', query: { data: array} });
        })
        .catch(function (error) {
          currentObj.output = error;
          alert('Unsuccessful Submission. ' + error);
        });
    }
  }

}
</script>

<style scoped>
h1 {
  font-weight: 700;
  text-align: center;
  margin-bottom: 80px;
}

h3 {
  color: #5E454B;
}

.searchBin {
  position: relative;
  display: inline-block;
  
  width: 500px;
}

.submit-btn {
  margin-top: 10px;
}

.addBin {
  position: relative;
  display: inline-block;
  width: 500px;
  left: 200px;
  bottom: 40px;
}

.or {
  margin-left: 70px;
}
</style>