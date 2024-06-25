type mobile =
| O of int
| B of mobile * mobile


let m1 = B(O(16), B(B(O(4), O(4)), O(8)));;


let rec t n = 
    if n=0 then O(1)
    else B((t (n-1)),(t (n-1)))



let rec pow a = function
  | 0 -> 1
  | 1 -> a
  | n -> 
    let b = pow a (n / 2) in
    b * b * (if n mod 2 = 0 then 1 else a) 
let rec u n = 
    if n=0 then O(1)
    else B(O(pow 2 (n-1) ), u (n-1))
    
    
let rec print_calder a s= 
    match a with  
        |B(e1, e2) -> s^" "^(print_calder e1 "")^" "^(print_calder e2 "")
        |O(e) -> string_of_int e
    ;;
 print_string (print_calder m1 "")

let m2 = t 3;; 
print_string("\n"^ print_calder m2 "")

let m3 = u 3;;
print_string("\n"^ print_calder m3 "")


let rec weight a = 
    match a with
        |B(e, e2) -> (weight e)+(weight e2)
        |O(e) -> e
    ;;
Printf.printf "\n%d %d\n" (weight m3)(weight m2)

let rec size a = 
    match a with
        |B(e, e2) -> (size e)+(size e2)
        |O(e) -> 1
    ;;
Printf.printf "%d %d\n" (size m3)(size m2)

let heigth a = 
    let rec h a= 
        match a with 
            |B(e, e2) -> 1+max (h e)(h e2)
            |O(e)->1
    in (h a) -1
    ;;
Printf.printf "%d %d\n" (heigth m3)(heigth m2)


let balanced' a = 
    let rec h a = 
    match a with 
        |B(e, e2) ->let t = h e in let t2 = h e2 in if t = t2&&t!=(-1)&&t2!=(-1) then t+t2 else -1
        |O(e) -> e
    in if(h a) = (-1) then false else true
    ;;
Printf.printf "%b %b \n" (balanced' m2) (balanced' m3) 
let mNB = B(O(15), B(B(O(4), O(4)), O(8)));;
Printf.printf "%b \n" (balanced' mNB)


let h = 1;; 
type annot_mobile =
| AO of int (* AO(r) *)
| AB of int * int * annot_mobile * annot_mobile (* AB(rx , ry , m1 , m2) *)
let sqrt n =
    let rec loop r s =
    (* Precondition : s = r*r *)
        if s > n then r - 1
        else loop ( r +1) ( s +2* r +1)
    in
    loop 0 0

let unit_radius = 4
let sq_unit = unit_radius * unit_radius
let radius w = sqrt ( sq_unit * w )



let uniform_width w = 
    w*(2*(h+radius 1)) 
    ;;
    


let rec annot_uniform m = 
    match m with 
        |B(e1, e2) -> AB(uniform_width (weight e1)/2 ,radius (weight e1 + weight e2) , (annot_uniform e1), (annot_uniform e2))
        |O(e)-> AO(radius e)
    ;;
    
let rec print_annot a = 
    match a with 
        |AO(e)-> "AO("^(string_of_int e)^")" 
        |AB(i, y, e1, e2) -> "("^(string_of_int i)^","^(string_of_int y)^","^(print_annot e1)^","^(print_annot e2)^")"
        ;;
let m4 = B( B ( O (1) , O (1)) , O (2));;
print_string(print_annot (annot_uniform m4))


let rec sep_width m= 
    match m with
        |B(e1, e2) -> (sep_width e1)+(sep_width e2)
        |O(e) -> 2*h+2*radius e
        
let rec left_width m = 
    match m with 
        |B(e1, e2)-> (left_width e2 + sep_width e1 - left_width e1)/2 + left_width e1
        |O(e) -> (2*h + 2*radius e)/2
        ;;
let right_width m = 
    sep_width m - left_width m 
    

let rec annot_sep m = 
    match m with 
        |B(e1, e2) -> AB((left_width e2 + right_width e1)/2, radius (weight e1 + weight e2), (annot_sep e1), (annot_sep e2))
        |O(e)-> AO(radius e)
        ;;
print_string("\n"^print_annot (annot_sep m4)) 


let adjust_profiles p1 p2 = 
    let rec adjust p1 p2 acc = 
        match p1, p2 with
            |[], [] -> acc
            |hd::tl, [] -> (List.rev (hd::tl))@acc
            |[], hd::tl -> (List.rev(hd::tl))@acc
            |hd1::tl1, hd2::tl2 -> adjust tl1 tl2 ([max hd1 hd2]@acc)
        in List.rev (adjust p1 p2 [])
        ;;
        
Printf.printf "\nadjust_profiles = [%s]\n" (String.concat "; " (List.map string_of_int (adjust_profiles [1; 2; 3] [1; 7; 3])));;
Printf.printf "\nadjust_profiles = [%s]\n" (String.concat "; " (List.map string_of_int (adjust_profiles [1; 2; 3] [1; 7; 3;1;2])));;
let rec left_profile b m = 
    match m with 
        |AB(rx, ry, e1, e2)->  [rx+1+b]@(adjust_profiles (left_profile (rx+b) e1) (left_profile (b-rx) e2)) 
        |AO(e) -> [b+e+1] 
    ;;
let am = AB (6, 8, AB (5, 5, AO 4, AO 4), AO 5);;
let result_left = left_profile 0 am in
Printf.printf "\nleft_profile 0 am = [%s]\n" (String.concat "; " (List.map string_of_int result_left));;
let rec right_profile b m = 
    match m with 
        |AB(rx, ry, e1, e2)->  [rx+1+b]@(adjust_profiles (right_profile (b-rx) e1) (right_profile (b+rx) e2)) 
        |AO(e) -> [b+e+1] 
    ;;
let result_right = right_profile 0 am in
Printf.printf "\nright_profile 0 am = [%s]\n" (String.concat "; " (List.map string_of_int result_right));;


let get_dist r1 l2 rx = 
    let rec aux r1 l2 rx (d:int) = 
        match r1, l2 with 
            |tl1::hd1, tl2::hd2 -> let d2 = ((rx -tl1) + (rx - tl2))/2 
            in if (d!=(-1)) then min d (aux hd1 hd2 rx d2)
            else aux hd1 hd2 rx d2
            |_, _->d
    in aux r1 l2 rx (-1)
    ;;
let l2 = left_profile 0 (AO 5) ;;
let r1 = right_profile 0 (AB (5 , 5 , AO 4 , AO 4));;
Printf.printf "\n%d" (get_dist r1 l2 8);;

let rec annot_nest m = 
    let m2 = annot_sep m in
    let rec aux m = 
        match m with 
            |AB(rx, ry, e1, e2) -> AB(rx - (get_dist (right_profile 0 e1) (left_profile 0 e2) rx), ry, aux e1, aux e2)
            |AO(e) -> AO(e)
        in aux m2
        ;;
        
print_string("\n"^(print_annot (annot_nest m4))^"\n");;


open Printf

let print_object cx cy r =
printf "<circle cx =\"%d\" cy =\"%d\" r =\"%d\" fill =\"black\"/>\n" cx cy r

let print_bar x1 y1 x2 y2 =
printf
"<line x1 =\"%d\" y1 =\"%d\" x2 =\"%d\" y2 =\"%d\" stroke-width =\"2px\" stroke =\"black\"/>\n"
( x1 -1) y1 ( x2 +1) y2

let print_thread x1 y1 x2 y2 =
printf "<line x1 =\"%d\" y1 =\"%d\" x2 =\"%d\" y2 =\"%d\" stroke =\"black\"/>\n" x1 y1 x2 y2

let generate_svg printer t =
printf "<svg xmlns =\"http://www.w3.org/2000/svg\">\n" ;
printer t ;
printf "</svg>\n"

let rec print_annot_mobile x y m= 
    match m with 
        |AO(e)-> print_object x (y+e) e
        |AB(rx, ry, e1, e2) -> print_bar (x-rx) (y+ry) (x+rx) (y+ry); 
        print_thread x y x (y+ry);
        print_thread (x-rx) (y+ry) (x-rx) (y+ry*2);
        print_thread (x+rx) (y+ry) (x+rx) (y+ry*2);
        print_annot_mobile (x-rx) (y+2*ry) e1;
        print_annot_mobile (x+rx) (y+2*ry) e2;
    ;;


let print_mobile m= 
    let aux m = 
        print_annot_mobile 100 0 m
    in 
    generate_svg aux (annot_nest m)
    ;;
(*print_mobile m4;;*)



let rec gen_fission k = 
    if k=0 then O(1)
    else if Random.int 10 <3 then O(pow 2 k)
    else B (gen_fission (k-1), gen_fission (k-1))
    ;;
    
(*print_mobile (gen_fission 5);;*)


let rec gen_fusion k = 
    if k=0 then O(1)
    else let m1 = gen_fusion (k-1) in
    let m2 = gen_fusion (k-1) in
    match m1, m2 with 
        |O(e), O(e2) -> if Random.int 10 <3 then O(e+e)
        else B(m1, m2) 
        |_, _ -> B(m1, m2)
        ;;
        
print_mobile (gen_fusion 5);;


