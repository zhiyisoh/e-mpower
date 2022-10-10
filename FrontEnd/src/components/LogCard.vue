<template >
    <div class="card-display container">
        <div class ="row ">
            <div class = "col-lg-4 mb-4" v-for = "log in logs.slice().reverse()">
                <div class="card h-100">
                    <img v-if = "log.itemType === ('Lithium Ion Battery')" class="card-img-top" src="/src/assets/x-LI-image.svg" alt="hehe" id="category-pic" img-top>
                    <img v-if = "log.itemType === ('Household Battery')" class="card-img-top" src="/src/assets/x-HB-image.svg" alt="hehe" id="category-pic" img-top>
                    <img v-if = "log.itemType === ('Consumer EV Battery')" class="card-img-top" src="/src/assets/x-EVB-image.svg" alt="hehe" id="category-pic" img-top>
                    <img v-if = "log.itemType === ('Consumer Lamp')" class="card-img-top" src="/src/assets/x-CL-image.svg" alt="hehe" id="category-pic" img-top>
                    <img v-if = "log.itemType === ('ICT')" class="card-img-top" src="/src/assets/x-ICT-image.svg" alt="hehe" id="category-pic" img-top>
                    <img v-if = "log.itemType === ('Electric Mobility')" class="card-img-top" src="/src/assets/x-EMD-image.svg" alt="hehe" id="category-pic" img-top>
                    <img v-if = "log.itemType === ('Large Household Appliance')" class="card-img-top" src="/src/assets/x-LHA-image.svg" alt="hehe" id="category-pic" img-top>
                    <img v-if = "log.itemType === ('Unregulated')" class="card-img-top" src="/src/assets/x-unregulated-image.svg" alt="hehe" id="category-pic" img-top>
                    
                    <div class="card-body">
                        <h5 class="card-title">{{log.itemName}}</h5>
                        <p class="card-text">Type: {{log.itemType}}</p>
                        <router-link :to ="`singlelog/${log.id}`"><button type="button" class="btn btn-outline-dark mt-auto mx-auto "
                                href="LogPage.vue">More</button>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data() {
        return {
            logs: [],
        };
    },
    created() {
            try {
                const API_URL = 'http://localhost:8080/api/logging';
                axios.get(API_URL).then(response => 
                    this.logs = response.data);
            }catch (error){
                console.log(error);
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
    padding: 7% 15%;
    text-align: centre;
    border-radius: 10%;
}

.card-img-top {
  width: 100%;
  object-fit: contain;
}
</style>