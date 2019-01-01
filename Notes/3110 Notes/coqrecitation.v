Inductive nat : Set :=
| O : nat
| S : nat -> nat
.

Fixpoint plus_nat (x y : nat) :=
  match x with
  | 0 => y
  | S x' => S(plus_nat x' y)
  end.

Lemma plus_0_n : forall n, plus_nat 0 n = n.
Proof.
  intro n.
  simpl. reflexivity.
Qed.

Proposition plus_n_0 : forall n, plus_nat n 0 = n.
Proof.
  intro. simpl.
  induction n.
  * reflexivity.
  * simpl. rewrite IHn. reflexivity.
Qed.

Lemma plus_x_sy : forall x y, plus_nat x (S y) = S (plus_nat x y).
Proof.
  intros.
  induction x.
  * reflexivity.
  * simpl. rewrite IHx. reflexivity.
Qed.

Theorem plus_comm : forall x y, plus_nat x y = plus_nat y x.
Proof.
  intros.
  induction x.
  * simpl. rewrite plus_n_0. reflexivity.
  * simpl. rewrite IHx. rewrite plus_x_sy. reflextivity/
Qed.

Check S (S (S 0) )
Check nat.
Check Set.
