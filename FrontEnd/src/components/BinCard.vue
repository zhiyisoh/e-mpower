<template >
    <div class="card-display container">
        <div class ="row ">
            <div class = "col-lg-6 mb-3" v-for = "bin in bins.slice().reverse()">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">{{bin.address}}</h5>
                        <p class="card-text"><strong>Postal Code:</strong> {{bin.postalCode}} <br><strong>Recycles ICT?</strong>  {{bin.ict}} 
                            <br><strong>Recycles Battery?</strong>  {{bin.battery}} <br><strong>Recycles Bulb?</strong>  {{bin.lamp}}</p>
                        
                        <router-link :to ="`editbin/${bin.id}`"><button type="button" class="editbin-btn btn btn-primary mt-auto mx-auto "
                                href="EditBin.vue">Edit</button>
                        </router-link>
                        <button v-on:click="JSalert(bin.id, this.$store.state.auth.user.accessToken, this.$router)" type="button" class="btn btn-outline-dark mt-auto mx-auto "
                                href="LogPage.vue">Delete</button>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import Swal from 'sweetalert2';

export default {
    data() {
        return {
            bins: [],
        };
    },
    created() {
        for(let i = 0; i < this.$route.query.data.length; i++){
            console.log(this.$route.query.data[i]);
            try {
                const API_URL = 'http://localhost:8080/api/bins/';
                axios.get(API_URL + this.$route.query.data[i], {
        headers: {
          'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken 
        }
      }).then(response => 
                    this.bins[i] = response.data, );
            }catch (error){
                console.log(error);
            }
        }
            
    }
    //test array
        //data() {
    //     return {
    //         logs: [
    //             {
    //                 itemType: "Household Battery",
    //                 itemName: "Testing",
    //                 createdDate: '2022-01-20',
    //                 itemNotes: 'omg',
    //                 id: '1'
    //             },
    //             {
    //                 itemType: "Consumer Lamp",
    //                 itemName: "Rice cooker",
    //                 createdDate: '2022-02-22',
    //                 itemNotes: 'i eat rice',
    //                 id: '2'
    //             },
    //             {
    //                 itemType: "Large Household Appliance",
    //                 itemName: "Washing machine",
    //                 createdDate: '2022-04-20',
    //                 itemNotes: 'wishy washi',
    //                 id: '3'
    //             },
    //             {
    //                 itemType: "ICT",
    //                 itemName: "Testing",
    //                 createdDate: '2022-06-09',
    //                 itemNotes: 'omg',
    //                 id: '4'
    //             }
    //         ]
    //     };
    // },
    
}


function JSalert(binid, accessToken, router){
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
              .delete(`http://localhost:8080/api/logging/deleteBin/${binid}`,
            {
        headers: {
          'Authorization': 'Bearer ' + accessToken 
        }
      })
              .then(response => {
                if (response.status != 200) {
                  throw new Error("Something went wrong");
                }
                return router.push('/BinResult');;
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
      'Bin has been deleted.',
      'success'
    )
    
  }
})
}
</script>

<style>
.card {
    text-align: center;
    background-color: #CEE5D0;
    border: 0;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    transition: 0.3s;
}

.card:hover {
    box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.card-display {
    margin: 20px 0;
    padding: 7% 5%;
    text-align: centre;
    border-radius: 10%;
}

.editbin-btn {
    margin-right: 20px !important;
}

.card-img-top {
  width: 100%;
  object-fit: contain;
}
</style>