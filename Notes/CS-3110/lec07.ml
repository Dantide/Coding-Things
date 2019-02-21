module type StackSig = sig (* A type of a module, SIGNATURES *)
  type 'a t (* Nothing other than what's declared in sig may be seen *)
  (* absract *)
  val empty : 'a t (* Cannot say what the value is *)
  val is_empty : 'a t -> bool
  val push : 'a -> 'a t -> 'a t
  val peek : 'a t -> 'a option
  val pop : 'a t -> 'a t option

end


module MyStack : StackSig = struct (* Module names must be Uppsercase *)
  type 'a t =
    | Empty (* Type names must be UpperCase *)
    | Entry of 'a * 'a t (** We can define a stack as the head of the stack,
                                 and the rest of the stack. *)
  
  let empty = Empty
  let is_empty s = (s = Empty)

  let push x s = Entry (x,s)

  exception EmptyStack (* Raise an exception *)
  let peek = function
    (*| Empty -> raise EmptyStack *)
    | Empty -> None (* Using options *)
    | Entry (x,_) -> Some x

  let pop = function
    | Empty -> None
    | Entry (_,s) -> Some s
end



module ListStack : StackSig = struct (* Module names must be Uppercase *)
  type 'a t = 'a list
  
  let empty = []
  let is_empty s = (s = [])

  let push x s = x :: s

  exception EmptyStack (* Raise an exception *)
  let peek = function
    (*| Empty -> raise EmptyStack *)
    | [] -> None (* Using options *)
    | x :: _ -> Some x

  let pop = function
    | [] -> None
    | _ :: s -> Some s
end