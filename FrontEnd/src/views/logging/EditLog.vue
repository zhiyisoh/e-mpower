<script setup>
import Footer from "../../components/Footer.vue";
</script>

<template>
  <div class="enter-log-view">


    <div class="enter">
      <h1>Hello {{this.$store.state.auth.user.username}}, made a mistake somewhere?</h1>
      <img src="/src/assets/recycle-bin.gif" alt="leaves" class="bin-icon">

      <Form @submit="onSubmit" :validation-schema="schema" v-on:submit.prevent="submitForm">
        <div class="form-group">
          <label for="itemType">Type of e-waste: </label>
          <select id="itemType" v-model="record.itemType" class="form-select" aria-label="Default select example">
            <option selected>-- Select type of e-waste --</option>
            <option value="ICT">Information and Communication Equipment (ICT)</option>
            <option value="Large Household Appliance">Large Household Appliance</option>
            <option value="Electric Mobility">Electric Mobility Device</option>
            <option value="Household Battery">Household Battery</option>
            <option value="Lithium Ion Battery">Lithium Ion Portable Battery</option>
            <option value="Consumer Lamp">Consumer Lamp</option>
            <option value="Consumer EV Battery">Consumer Electric Vehicle Battery</option>
            <option value="Unregulated">Unregulated E-Waste</option>
          </select>
          <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
            {{ message }}
          </div>

          <p>
            <a class="help-btn btn btn-primary btn-sm" data-toggle="collapse" href="#collapseExample" role="button"
              aria-expanded="false" aria-controls="collapseExample">
              Unsure of your type of e-waste?
            </a>
          </p>
          <div class="collapse" id="collapseExample">
            <div class="card card-body">
              <p>If your item is not found in any of these categories, please indicate "Unregulated E-Waste"</p>
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
                  Network and Set-up boxes
                </li>
                <li>
                  Desktop Monitors
                </li>
              </ul>

              <h5>Large Household Appliance</h5>
              <ul class="type-list">
                <li>
                  Consumer Refrigerators less than 900L
                </li>
                <li>
                  Air-Conditioners
                </li>
                <li>
                  Washing Machines
                </li>
                <li>
                  Dryers
                </li>
                <li>
                  Televisions
                </li>
              </ul>

              <h5>Electric Mobility Device</h5>
              <ul class="type-list">
                <li>
                  Personal Mobility Device
                </li>
                <li>
                  Power Assisted Bicycle
                </li>
                <li>
                  Electric Mobility Scooter
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

              <h5>Lithium Ion Portable Battery</h5>
              <ul class="type-list">
                <li>
                  Powerbank
                </li>
                <li>
                  Mobile Telephone Battery
                </li>
                <li>
                  Laptop Battery
                </li>
              </ul>

              <h5>Consumer Lamp</h5>
              <ul class="type-list">
                <li>
                  Bulb
                </li>
                <li>
                  Fluorescent Tube
                </li>
              </ul>

              <h5>Consumer Electric Vehicle Battery</h5>

            </div>
          </div>
        </div>

        <div class="form-group">
          <label for="itemName">Item name: </label>
          <Field name="itemName">
            <input id="itemName" v-model="record.itemName" type="text" class="form-control"/></Field>
          <ErrorMessage name="username" class="error-feedback" />
        </div>

        <div class="form-group">
          <label for="itemQuantity">Item Quantity: </label>
          <input id="itemQuantity" v-model="itemQuantity" type="text" class="form-control" />
          <ErrorMessage name="username" class="error-feedback" />
        </div>

        <div class="form-group">
          <label for="createdDate">Date (YYYY-MM-DD): </label>
          <input id="createdDate" v-model="record.createdDate" type="text" class="form-control" />
        </div>

        <div class="form-group">
          <label for="itemNotes">Notes: </label>
          <input id="itemNotes" v-model="record.itemNotes" type="text" class="form-control" />
        </div>  
       
        <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
          {{ message }}
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            Submit
          </button>
        </div>
      </Form>
    </div> 
    <Footer />
  </div>

</template>
  

<script>
import axios from 'axios';



export default {

  name: 'LogEntry',
  el: "#app",
  data() {
    return {
      itemType: "",
      itemName: "",
      itemQuantity: "",
      createdDate: "",
      itemNotes: '',
      record: []
    }
  }, methods: {
    onSubmit(e) {
      e.preventDefault();
     var message = ""

      if (!this.itemName) {
        message+='❌ Item Name field is required\n'
      }

      if (!this.createdDate) {
        message+='❌ Date field is required\n'
      }

      if (!this.itemQuantity) {
        message+='❌ Item Quantity field is required\n'
      }

      if(message){
        alert (message)
        return
      }

      let currentObj = this;
      let self = this;
      const API_URL ='http://localhost:8080/api/logging/updatelog/';
      
      axios.put(API_URL + this.$store.state.auth.user.id + '/' + this.$route.params.id, {
        itemName: this.record.itemName,
        itemQuantity: this.record.itemQuantity,
        itemType: this.record.itemType,
        itemNotes: this.record.itemNotes,
        createdDate: this.record.createdDate
      }, {
        headers: {
          'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken 
        }
      })
        .then(function (response) {
          currentObj.output = response.data;
          history.back();
        })
        .catch(function (error) {
          currentObj.output = error;
          alert('Unsuccessful Submission. ' + error);
        });
    }
  }, mounted(){
    const url = "http://localhost:8080/api/logging/"; //to be changed
    axios.get(url + this.$route.params.id)
    .then(response => {
      this.record = response.data;

    }).catch((error) => {
        this.error = "Error!  " + error;
      });

  
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
  font-size: 24px;
}

.enter-log-view {
  font-family: 'Merriweather', sans-serif;
  color: #5E454B;

}

.bin-icon {
  width: 200px;
  margin: 5%;
}

.back {
  background-color: #5E454B;
  font-family: 'Merriweather', sans-serif;
  color: white;
  border-radius: 8px;
  border-color: transparent;
  margin-left: 40px;
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