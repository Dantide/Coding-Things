Require Import List.
Import ListNotations.

Module MyStack.

Definition stack (A:Type) := list A.

Definition empty {A:Type} : stack A := nil.

Definition is_empty {A:Type} (s : stack A) : bool :=
  match s with
  | nil => true
  | _::_ => false
  end.

Definition push {A:Type} (x : A) (s : stack A) : stack A :=
  x::s.

Definition peek {A:Type} (s : stack A) : option A :=
  match s with
  | nil => None 
  | x::_ => Some x
  end.

Definition pop {A:Type} (s : stack A) : option (stack A) :=
  match s with
  | nil => None 
  | _::xs => Some xs
  end.

Definition size {A:Type} (s : stack A) : nat := 
  length s.

Theorem empty_is_empty : forall (A : Type),
  @is_empty A empty = true.
Proof. trivial. Qed.


End MyStack.

Require Import Extraction.
Extraction "mystack.ml" MyStack.



(* Factorial *)

Inductive factorial_of : nat -> nat -> Prop :=
  | factorial_of_zero : factorial_of 0 1
  | factorial_of_succ : forall (a b : nat),
    factorial_of a b -> factorial (S a) ((S a) * b).

Fixpoint fact (n : nat) :=
  match n with
  | 0 => 1
  | S k => n * (fact k)
  end.

Theorem fact_correct : forall (n : nat),
  factorial_of n (fact n)
Proof.
  intros.
  induction n as [ | k IH].
  - simpl
Qed.
