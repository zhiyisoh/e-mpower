<script setup>
    import Footer from "../../components/Footer.vue";
</script>

<template>
    <div class="locator-view">
        <div class="form-container">
            <div class="form-header">
                <h1>Locate a Bin!</h1>
                <br>
                <p class="desc">With E-MPOWER's Bin Locator feature, 
                you can find the nearest available bin to recycle your e-waste. 
                <br><strong class="impt">IMPORTANT:</strong> If your item type is not in the options,
                 you are <strong>NOT</strong> able to recycle via e-waste recycling bins. Collection point will be at 20 Tuas Loop (Opening Hours: Mon-Fri, 9AM - 5.30PM). Click <a href="https://alba-ewaste.sg/drop-off-locations/dd/">here</a> for more information</p>
                
            </div>
            
            <div class="form-locate">
                <Form @submit="onSubmit" :validation-schema="schema" v-on:submit.prevent="submitForm">
                    <div class="form-group">
                        <label for="location">Postal Code / Address</label>
                        <input name="location" v-model="location" type="text" class="form-control" />
                        <button type="button" class="btn btn-primary saved-locations-btn" data-bs-toggle="collapse" href="#locationsCollapse"
                          aria-expanded="false" aria-controls="locationsCollapse">
                          View Saved Locations</button>
                        <div class="collapse" id="locationsCollapse">
                          <div class="card card-body">
                            <h5>Saved Locations:</h5>
                          </div>
                        </div>
                        <ErrorMessage name="username" class="error-feedback" />
                        
                    </div>

                    <div class="form-group name-field">
                        <label for="itemName">Item Type:</label>

                        
                      <p>
                        <a class="help-btn btn btn-outline btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button"
                          aria-expanded="false" aria-controls="collapseExample">
                          Unsure of your type of e-waste? Click here.
                        </a>
                      </p>
                      <div class="collapse" id="collapseExample">
                        <div class="card card-body">
                          <p>If your item is not found in any of these categories, it is an unregulated waste and is <strong>not recyclable</strong>. 
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


                        <div class="item-types">

                            <div>Picked: {{ recycleType }} </div>

                            <input type="radio" class="btn-check" name="options" id="ICT" autocomplete="off" value="ICT" v-model="recycleType" checked />
                            <label class="btn btn-outline-dark" for="ICT">ICT</label>

                            <input type="radio" class="btn-check" name="options" id="Batteries" autocomplete="off" value="Batteries" v-model="recycleType" />
                            <label class="btn btn-outline-dark" for="Batteries">Batteries</label>

                            <input type="radio" class="btn-check" name="options" id="CL-B" autocomplete="off" value="Bulb" v-model="recycleType"/>
                            <label class="btn btn-outline-dark" for="CL-B">Consumer Lamp (Bulb)</label>

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
        </div>
    </div>
    
    
    <Footer/>
</template>

<script>
import axios from 'axios';

  export default {
    name: 'LocatorEntry',
    data() {
      return {
        location: '',
        recycleType: '',
        longitude: '',
        latitude:''
      }
  }, methods: {
    async onSubmit(e) {
      e.preventDefault();

      if(!(this.location)){
        alert('❌ Address field is required')
        return
      }
      

      let currentObj = this;
      let self = this;

      const location_url = 'https://developers.onemap.sg/commonapi/search?searchVal=' + this.location + '&returnGeom=Y&getAddrDetails=Y&pageNum=1';
      //console.log(location_url);
      await axios.get( location_url ,
        {
         params: {

         } 
        })
      .then(function(response){
        currentObj.longitude = response.data.results[0].LONGITUDE;
        currentObj.latitude = response.data.results[0].LATITUDE;
        //console.log(response.data.results[0].LATITUDE);
        //alert(currentObj.longitude + ' and ' + currentObj.latitude);
      })
      .catch(function(error){
        alert('Invalid Location. ' + error);
      });
      
      
      
      const API_URL ='http://localhost:8080/api/bins/findNearestBin';
      await axios.post(API_URL, {
        longitude: this.longitude,
        latitude: this.latitude,
        recycleType: this.recycleType
      }, {
        headers: {
          'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken 
        }
      })
        .then(function (response) {
          currentObj.output = response.data;
          console.log(response.data);
          self.$router.push('/returnedbins/' + response.data);
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
}

label {
    margin: 7px 5px 0;
    font-size: 20px;
  }
  
    .name-field {
        margin-top: 60px;
    }
    .locator-view {
        text-align: center;
    }

    .desc {
        width: 80%;
        margin: 0 auto;
    }

    .impt {
        color: red;
        
    }
    .form-locate {
        width: 90%;
        margin: 30px auto;
        
    }

    .saved-locations-btn {
        margin-top: 10px;
        float: right;
    }

    .item-types {
        margin: 30px 0 50px;
    }

    .card {
        background-color: #CEE5D0;
    }
    .btn-check:checked+.btn {
        background-color:#5E454B;
        color: white !important;
    }
    
</style>