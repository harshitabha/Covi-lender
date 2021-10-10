use chrono::{Duration, Utc};

fn main(){
    dateGetter("US", "UK", 14);

    let Qua1: i8 = 14;
    let Qua2: i64 = 10;
    let Qua3: i8 = 7;

    let dt = Utc::now() + Duration::days(Qua2);
    println!("today date + {} days {}", Qua2, dt);
}



fn dateGetter(arrival: &str, departure: &str, date: i8){
    let Qua1: i8 = 14;
    let Qua2: i8 = 10;
    let Qua3: i8 = 7;

    let QuaSetter = 0;

    if departure == "US"{
        if arrival == "UK"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "India"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "China"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "Japan"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
    }
    else if departure == "UK"{
        if arrival == "US"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "India"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "China"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "Japan"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
    }
    else if departure == "India"{
        if arrival == "US"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "UK"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "China"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "Japan"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
    }
    else if departure == "China"{
        if arrival == "US"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "India"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "Japan"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "UK"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
    }else{
        if arrival == "US"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "India"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "China"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
        if arrival == "UK"{
            let Quarantine: i8 = QuaSetter + Qua2;
        }
    }

 }