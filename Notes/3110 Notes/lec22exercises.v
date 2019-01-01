(**
 * Proofs are Programs
 *)

(* 
Pick a few of these propositions, and prove them by directly
writing down the Coq program that is the proof, rather than
using tactics to help construct the proof.  
  0: (A -> B) -> ((B -> C) -> (A -> C))
  1: A -> (A \/ B)
  2: B -> (A \/ B)
  3: (A -> C) -> ((B -> C) -> ((A \/ B) -> C))
  4: A /\ B -> A
  5: A /\ B -> B
  6: (C -> A) -> ((C -> B) -> (C -> (A /\ B)))
  7: (A -> (B -> C)) -> ((A /\ B) -> C)
  8: ((A /\ B) -> C) -> (A -> (B -> C))
  9: (A /\ ~A) -> B
  10: (A -> (A /\ ~A)) -> ~A
  11: A -> (A -> B) -> B 

Here's an example of what we mean, using the first proposition
from above:
*)

Definition example : forall A B C : Prop, 
  (A -> B) -> ((B -> C) -> (A -> C))
:=
  fun (A B C : Prop) (ab : A -> B) (bc : B -> C) (a : A) =>
    bc (ab a).

(* You will need to use pattern matching for proofs/programs involving
   conjunction, disjunction, and negation.  Specifically, [conj] will
   be useful for conjunction, [or_introl] and [or_intror] for disjunction,
   and [not] and [False_rect] for negation.  You can review those
   by examing the output of these: *)

Print and.
Print or.
Print not.
Print False_rect.

Definition prop1 : forall (A B : Prop),
  A -> (A \/ B)
:=
  fun (A B : Prop) (a : A) =>
    or_introl a.

Definition prop2 : forall (A B : Prop),
  B -> (A \/ B)
:=
  fun (A B : Prop) (b : B) =>
    or_intror b.

Definition prop3 : forall (A B C : Prop),
  (A -> C) -> ((B -> C) -> ((A \/ B) -> C))
:=
  fun (A B C : Prop) a_to_c b_to_c a_or_b =>
    match a_or_b with
    | or_introl a => a_to_c a
    | or_intror b => b_to_c b
    end.

Definition prop4 : forall (A B : Prop),
  A /\ B -> A
:=
  fun (A B : Prop) (a_and_b : A /\ B) =>
    match a_and_b with conj a _ => a end.

Definition prop5 : forall (A B : Prop),
  A /\ B -> B
:=
  fun (A B : Prop) (a_and_b : A /\ B) =>
    match a_and_b with conj _ b => b end.

Definition prop6 : forall (A B C : Prop),
  (C -> A) -> ((C -> B) -> (C -> (A /\ B)))
:=
  fun (A B C : Prop) (c_to_a : C -> A) (c_to_b : C -> B) (c : C) =>
    conj (c_to_a c) (c_to_b c).

Definition prop7 : forall (A B C : Prop),
  (A -> (B -> C)) -> ((A /\ B) -> C)
:=
  fun (A B C : Prop) a_b_to_c a_and_b =>
    match a_and_b with conj a b => a_b_to_c a b end.

Definition prop8 : forall (A B C : Prop),
  ((A /\ B) -> C) -> (A -> (B -> C))
:=
  fun (A B C : Prop) a_and_b_to_c a b =>
    a_and_b_to_c (conj a b).

Definition prop9 : forall (A B : Prop),
  (A /\ ~A) -> B
:=
  fun (A B : Prop) a_and_not_a =>
    match a_and_not_a with
    | conj a na => False_rect B (na a)
    end.

Definition prop10 : forall (A : Prop),
  (A -> (A /\ ~A)) -> ~A
:=
  fun (A : Prop) a_to_a_and_not_a =>
    fun (a : A) =>
      match a_to_a_and_not_a a with
      | conj a na => na a
      end.

Definition prop11 : forall (A B : Prop),
  A -> (A -> B) -> B
:=
  fun (A B : Prop) (a : A) (a_to_b : A -> B) =>
    a_to_b a.
