(* Evaluates to f: 'a -> 'b *)
let rec f x = f x

(* A recursive definition of an infinite list of ones *)
let rec ones = 1 :: ones

(* a is a list of [0;1;<cycle>] and b is a list of [1;0;<cycle>] *)
let rec a = 0 :: b
    and b = 1 :: a

(* Infinite list with no Nil Constructor, doesn't work with set of natural nunmbers *)
type 'a old_stream = 
  | Cons of 'a * 'a old_stream

(* Same thing as [ones] above *)
let rec new_ones : int old_stream = Cons (1,new_ones)

(* Delayed evaluation of the infinite list *)
type 'a stream =
  (* Uses a THUNK: a delayed computation in a function *)
  | Cons of 'a * (unit -> 'a stream)

(* Stream of infinite numbers from a number [n] *)
let rec from n = Cons (n, fun _ -> from (n+1))

(* Stream of natural numbers *)
let nats = from 0

(* Get the head value of the stream *)
let hd (Cons (h,_)) = h

(* Get the next part of the stream *)
let tl (Cons (_,t)) = t ()

(* Get a list of size n from a stream *)
let rec take (n:int) (s:'a stream) : 'a list =
  if n=0 then []
  else (hd s) :: take (n-1) (tl s)

(* Create a stream as a sum of two streams *)
let rec sum (Cons (h_a, tf_a)) (Cons (h_b, tf_b)) : int stream =
  Cons (h_a+h_b, (fun () -> sum (tf_a ()) (tf_b ()) ))

(* Exponetial amount of computatation  *)
let rec fibs =
  Cons(1, (fun () ->
    Cons(1, fun () -> 
      sum fibs (tl fibs))))