Require Import List.
Import ListNotations.

Locate "++".
Print app.

Theorem app_nil : forall (A : Type)
  (lst: list A),
  lst ++ [] = lst.
Proof.
  intros A lst.
  induction lst as [ | h t].
  - simpl. trivial.
  - simpl. rewrite IHt. trivial.
Qed.

Locate "=".
Check eq.
Check Nat.eqb.
Require Import Arith.

Fixpoint sum_to (n : nat) := nat.
  match n with
  | 0 => 0
  | S k => n + sum_to k
  end.