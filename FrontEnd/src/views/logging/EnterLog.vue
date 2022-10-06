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

      <Form @submit="" :validation-schema="schema">
        <div class="form-group">
          <label for="type">Type of e-waste: </label>
          <select class="form-select" aria-label="Default select example">
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

          <p>
            <a class="help-btn btn btn-primary btn-sm" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
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
          <label for="name">Item name: </label>
          <input name="name" type="text" class="form-control" />
        </div>

        <div class="form-group">
          <label for="date">Date (YYYY-MM-DD): </label>
          <input name="date" type="text" class="form-control" />
        </div>
        <div class="form-group">
          <label for="notes">Notes: </label>
          <input name="notes" type="text" class="form-control" />
        </div>
        <div class="form-group">
          <label for="image">Upload an image of your e-waste! 🗑️</label>
          <div id="preview">
            <input type="file" accept="image/*" @change="onChange" />
            <img v-if="item.imageUrl" :src="item.imageUrl" />
          </div>
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
  <!-- <input type="file" accept="image/*" @change="uploadImage($event)" id="file-input"> -->
  <!-- code to allow user to upload image -->
  <!-- <v-file-input
      v-model="file"
      chips
      accept="image/*"
      label="Image"
    /> -->



</template>
  
<!-- <script>
methods: {
  method to upload image, still figuring it out
  uploadImage(event) {

    const URL = 'http://foobar.com/upload';

    let data = new FormData();
    data.append('name', 'my-picture');
    data.append('file', event.target.files[0]);

    let config = {
      header: {
        'Content-Type': 'image/png'
      }
    }

    axios.put(
      URL,
      data,
      config
    ).then(
      response => {
        console.log('image upload response > ', response)
      }
    )
  }
}
</script> -->

<script>
export default {

  name: 'imageUpload',
  data() {
    return {
      item: {
        type: '',
        date: '',
        notes: '',
        image: null,
        imageUrl: null
      }
    }
  },
  methods: {
    onSubmit(e) {
      e.preventDefault()
      if (!this.type) {
        alert('Please enter type of e-waste')
        return
      }
      const NewInformation = {
        id: Math.floor(Math.random() * 100000),
        name: this.name,
        //age : this.age,
        //reminder : this.reminder
      }
      this.$emit('add-information', newInformation)
      this.name = ' '
      //this.age = ' '
    }
    
    // onChange(e) { -- check how to add 2 methods
    //   const file = e.target.files[0]
    //   this.image = file
    //   this.item.imageUrl = URL.createObjectURL(file)

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