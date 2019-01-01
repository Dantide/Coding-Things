(* Functors *)
module type Stack = sig
  type 'a t
  val empty : 'a t
  val push : 'a -> 'a t -> 'a t
end

module ListStack : Stack = struct
  type 'a t = 'a list
  let empty = []
  let push x s = x :: s
end

(* functors are functions on structures *)

module type StackSig = sig 
  type 'a t
  val empty : 'a t
  val push : 'a -> 'a t -> 'a t
  val peek : 'a t -> 'a
end 

module ListStack : StackSig = struct
  type 'a t = 'a list
  let empty = []
  let push x s = x :: s
  let peek = function [] -> failwith "empty" | x :: _ -> x
end

let _ = assert (ListStack.(empty |> push 1 |> peek) = 1)

module MyStack : StackSig = struct
  type 'a t = Empty | Entry of 'a * 'a t
  let empty = Empty
  let push x s = Entry (x, s)
  let peek = function Empty -> failwith "empty" | Entry(x,_) -> x
end

let _ = assert (MyStack.(empty |> push 1 |> peek) = 1)

(* Can we get rid of duplicated code in asserts? *)

let f = fun x -> x + 1

(*
module StackTester (S : StackSig) = struct 
  let the_unit_test = assert (S.(empty |> push 1 |> peek) = 1)
end *)

(* Same thing as: *)

module StackTester = functor (S : StackSig) -> struct
  let the_unit_test = assert (S.(empty |> push 1 |> peek) = 1)
end 

module MyStackTester = StackTester(MyStack)
module ListStackTester = StackTester(ListStack)