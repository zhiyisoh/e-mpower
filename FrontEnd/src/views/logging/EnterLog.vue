<script setup>
import Footer from "../../components/Footer.vue";
</script>

<template>
  <div class="enter-log-view">
    <form>
      <input class="back" type="button" value="Back to past logs" onclick="history.back()">
    </form>


    <div class="enter">
      <h1>Hello {{this.$store.state.auth.user.username}}, ready to recycle?</h1>
      <img src="/src/assets/recycle-bin.gif" alt="leaves" class="bin-icon">

      <Form @submit="onSubmit" :validation-schema="schema" v-on:submit.prevent="submitForm">
        <div class="form-group">
          <label for="itemType">Type of e-waste: </label>
          <select id="itemType" v-model="itemType" class="form-select" aria-label="Default select example">
            <option selected>-- Select type of e-waste --</option>
            <option value="ICT">Information and Communication Equipment (ICT)</option>
            <option value="Household Battery">Household Battery</option>
            <option value="Consumer Lamp">Consumer Lamp</option>
          </select>

          <p>
            <a class="help-btn btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button"
              aria-expanded="false" aria-controls="collapseExample">
              Unsure of your type of e-waste?
            </a>
          </p>
          <div class="collapse" id="collapseExample">
            <div class="card card-body">
              <p>If your item is not found in any of these categories, they cannot be thrown in the bins. 
                We would recommend for you to bring your E-Waste down to the nearest Cash for Trash (CFT) station. <a href="https://www.alba-wh.sg/map.html">Click here for more information.</a></p>
              <h5>Information and Communication Equipment (ICT)</h5>
              <ul class="type-list">
                <li>
                  Printers less than 20kg
                </li>
                <li>
                  Computers and Laptops
                </li>
                <li>
                  Mobile Phones and Tablets
                </li>
                <li>
                  Desktop Monitors
                </li>
                <li>
                  Powerbank
                </li>
              </ul>

              <h5>Household Battery</h5>
              <ul class="type-list">
                <li>
                  D
                </li>
                <li>
                  C
                </li>
                <li>
                  AA
                </li>
                <li>
                  AAA
                </li>
                <li>
                  AAAA
                </li>
                <li>
                  N
                </li>
                <li>
                  9-volt
                </li>
                <li>
                  Button Cell
                </li>
              </ul>

              <h5>Consumer Lamp</h5>
              <ul class="type-list">
                <li>
                  Bulb
                </li>
              </ul>
            </div>
          </div>
        </div>

        <div class="form-group">
          <label for="itemType"> Name of e-waste: </label>
          <select id="itemType" v-model="itemName" class="form-select" aria-label="Default select example">
            <option selected>-- Select type of e-waste --</option>
            <option v-show="itemType === ('ICT')" value="Computer">Computer/Laptop (ICT)</option>
            <option v-show="itemType === ('ICT')" value="Phone">Mobile Phone/Tablet (ICT)</option>
            <option v-show="itemType === ('ICT')" value="Printer">Printer (ICT)</option>
            <option v-show="itemType === ('ICT')" value="Powerbank">Power Bank (ICT)</option>
            <option v-show="itemType === ('ICT')" value="ComputerTV">Television/Desktop Monitor (ICT)</option>

            <option v-show="itemType === ('Household Battery')" value="AAAA">AAAA (Battery)</option>
            <option v-show="itemType === ('Household Battery')" value="AAA">AAA (Battery)</option>
            <option v-show="itemType === ('Household Battery')" value="AA">AA (Battery)</option>
            <option v-show="itemType === ('Household Battery')" value="D">D (Battery)</option>
            <option v-show="itemType === ('Household Battery')" value="C">C (Battery)</option>
            <option v-show="itemType === ('Household Battery')" value="9-volt">9-volt (Battery)</option>
            <option v-show="itemType === ('Household Battery')" value="ButtonCell">ButtonCell (Battery)</option>

            <option v-show="itemType === ('Consumer Lamp')" value="Bulb">Bulb (Consumer Lamp)</option>
          </select>
          <ErrorMessage name="username" class="error-feedback" />
        </div>

        <div class="form-group">
          <label for="createdDate">Date (YYYY-MM-DD): </label>
          <input id="createdDate" v-model="createdDate" type="text" class="form-control" />
        </div>

        <div class="form-group">
          <label for="itemNotes">Notes: </label>
          <input id="itemNotes" v-model="itemNotes" type="text" class="form-control" />
        </div>  

        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            Submit
          </button>
        </div>
      </Form>
    </div> 
    <Footer/>
  </div>

</template>
  

<script>
import axios from 'axios';


export default {

  name: 'LogEntry',
  data() {
    return {
      itemType: '',
      itemName: '',
      createdDate: '',
      itemNotes: ''
    }
  }, methods: {
    onSubmit(e) {
      e.preventDefault();

      if(!(this.itemName || this.createdDate)){
        alert('??? Item Name field is required \n??? Date field is required')
        return
      }
      if (!this.itemName) {
        alert('??? Item Name field is required ')
        return
      }

      if (!this.createdDate) {
        alert('??? Date field is required')
        return
      }

      let currentObj = this;
      let self = this;
      const API_URL ='http://localhost:8080/api/logging/addlog/';
      axios.post(API_URL + this.$store.state.auth.user.id, {
        itemName: this.itemName,
        itemType: this.itemType,
        itemNotes: this.itemNotes,
        createdDate: this.createdDate
      }, {
        headers: {
          'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken 
        }
      })
        .then(function (response) {
          currentObj.output = response.data;
          self.$router.push('/logging');
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
  text-align: center;
}

h5 {
  font-weight: 700;
}

label {
  margin: 7px 5px 0;
  font-size: 20px;
}


.bin-icon {
  width: 200px;
  margin: 5%;
}

.back {
  background-color: #5E454B;
  color: white;
  border-radius: 8px;
  border-color: transparent;
  margin-left: 40px;
  padding: 0.5% 1%;
}

.form-group {

  margin: auto;
  padding-top: 20px;
  width: 90%;
  height: 70%;
}

.help-btn {
  margin-top: 20px;
  margin-left: 250px;
}

.type-list {
  text-align: left;
}

.enter {
  width: 60%;
  text-align: center;
  margin: 10px auto;
  background-color: white;
  border-radius: 10%;
  padding: 7%;
}

#preview {
  font-size: 16px;
  margin: 20px auto;
}
</style>