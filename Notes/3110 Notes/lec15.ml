(** A *)
module type Promise = sig

  type 'a state = Pending | Resolved of 'a | Rejected of exn
  type 'a promise
  type 'a resolver

  val state : 'a promise -> 'a state
  val resolve : 'a resolver -> 'a -> unit
  val reject : 'a resolver -> exn -> unit
  val make : unit -> exn -> unit
  val return : 'a -> 'a promise
end

module Promise : Promise = struct

  type 'a state = Pending | Resolved of 'a | Rejected of exn
  type 'a promise = 'a state ref
  type 'a resolver = 'a state -> unit

  let state p = !p
  let resolve r x  = r (Resolved x)
  let reject r x = r (Rejected x)
  let make () = ()
  let return : 'a -> 'a promise
end

Lwt_io.(read_char stdin)