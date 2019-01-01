(* REFS utop: *)
let x = 42 (* val x : int = 42 *)
let a = ref 42 (* val a : int ref = {contents = 42} *);
a := 43 (* unit = () *);
a (* int ref = {contents = 43} *);
a := "hello" (* Some type error *)

(* COUNTER *)
let counter = ref 0

let next_val () =
  (* counter := (!counter) + 1; (* Semicolon used between imperative functions *) *)
  incr counter;
  !counter
(* also given by incr *)

let ignore _ = ()
(*  *)

let _ = a := 42; 43

(* Damn that's beautiful mmmm *)
let next_val_1 = 
  let counter = ref 0
  in fun () ->
    incr counter;
    !counter

(** MUTABLE FIELDS *)
type point = {x:int; y:int; c:string}
type mut_point = {x:int; y:int; mutable c:string}
let p = {x=0; y=0; c="red"}
p.c <- "black"

let 'a t = {mutable r : 'a ref} (* Jesus how disgusting, a mutable pointer to a
                                   mutable pointer *)
let 'a ref = {mutable contents : 'a}

t.r <- 
t.r :=   